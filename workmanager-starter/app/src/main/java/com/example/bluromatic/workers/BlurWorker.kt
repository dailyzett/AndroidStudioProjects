package com.example.bluromatic.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bluromatic.DELAY_TIME_MILLIS
import com.example.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "BlurWorker"

class BlurWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        makeStatusNotification(
            message = applicationContext.resources.getString(R.string.blurring_image),
            context = applicationContext,
        )

        return withContext(Dispatchers.IO) {

            delay(DELAY_TIME_MILLIS)

            return@withContext try {
                val picture = BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.android_cupcake
                )

                val output: Bitmap = blurBitmap(picture, 1)

                val outputUrl: Uri = writeBitmapToFile(applicationContext, output)

                makeStatusNotification(
                    "Output is $outputUrl",
                    applicationContext
                )

                Result.success()
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_applying_blur),
                    throwable
                )
                Result.failure()
            }
        }
    }
}