/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bluromatic.data

import android.content.Context
import android.net.Uri
import androidx.lifecycle.asFlow
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.bluromatic.IMAGE_MANIPULATION_WORK_NAME
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.TAG_OUTPUT
import com.example.bluromatic.getImageUri
import com.example.bluromatic.workers.BlurWorker
import com.example.bluromatic.workers.CleanupWorker
import com.example.bluromatic.workers.SaveImageToFileWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull

class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {

    private val workManager = WorkManager.getInstance(context)

    override val outputWorkInfo: Flow<WorkInfo> = workManager
        .getWorkInfosByTagLiveData(TAG_OUTPUT)
        .asFlow()
        .mapNotNull { if (it.isNotEmpty()) it.first() else null }

    private var imageUri = context.getImageUri()

    /**
     * 블러를 적용하고 결과 이미지를 저장할 작업 요청을 생성합니다.
     * @param blurLevel 이미지를 흐리게 처리할 양
     */
    override fun applyBlur(blurLevel: Int) {
        // Create WorkRequest to blur the image
        var continuation = workManager.beginUniqueWork(
            IMAGE_MANIPULATION_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.from(CleanupWorker::class.java)
        )

        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()

        blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri))

        // Start the work
        continuation = continuation.then(blurBuilder.build())

        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .addTag(TAG_OUTPUT)
            .build()

        continuation = continuation.then(save)

        continuation.enqueue()
    }

    /**
     * 진행 중인 작업 요청 취소
     * */
    override fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }

    /**
     * 적용할 블러 양을 업데이트할 블러 레벨과 작동할 Uri를 포함하는 입력 데이터 번들을 생성합니다.
     * @return 이미지 Uri를 문자열로, 블러 레벨을 정수로 포함하는 데이터
     */
    private fun createInputDataForWorkRequest(blurLevel: Int, imageUri: Uri): Data {
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
        return builder.build()
    }
}
