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

package com.example.waterme.data

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.waterme.model.Plant
import com.example.waterme.worker.WaterReminderWorker
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(context: Context) : WaterRepository {
    private val workManager = WorkManager.getInstance(context)

    override val plants: List<Plant>
        get() = DataSource.plants

    override fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String) {

        val workName = plantName

        // workName 확인을 위해 Log.e 추가
        Log.e("WorkManagerWaterRepo", "Scheduling reminder for $plantName with work name: $workName")

        //Data.Builder로 data라는 변수를 만듭니다. 데이터는 WaterReminderWorker.nameKey가 키이고 scheduleReminder()에 전달된 plantName이 값인 단일 문자열 값으로 구성되어야 합니다.
        val data = Data.Builder()
            .putString(WaterReminderWorker.nameKey, workName)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(data)
            .setInitialDelay(duration, unit)
            .build()

        workManager.enqueueUniqueWork(workName, ExistingWorkPolicy.REPLACE, workRequest)
    }
}
