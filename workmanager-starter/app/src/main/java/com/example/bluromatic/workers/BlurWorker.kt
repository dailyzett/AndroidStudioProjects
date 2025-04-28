package com.example.bluromatic.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bluromatic.R

private const val TAG = "BlurWorker"

class BlurWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        makeStatusNotification(
            message = applicationContext.resources.getString(R.string.blurring_image),
            context = applicationContext,
        )

        TODO()
    }
}