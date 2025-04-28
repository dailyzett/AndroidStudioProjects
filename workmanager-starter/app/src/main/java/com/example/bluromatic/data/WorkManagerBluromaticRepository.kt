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
import androidx.work.Data
import androidx.work.WorkInfo
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {

    override val outputWorkInfo: Flow<WorkInfo?> = MutableStateFlow(null)

    /**
     * 블러를 적용하고 결과 이미지를 저장할 작업 요청을 생성합니다.
     * @param blurLevel 이미지를 흐리게 처리할 양
     */
    override fun applyBlur(blurLevel: Int) {}

    /**
     * 진행 중인 작업 요청 취소
     * */
    override fun cancelWork() {}

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
