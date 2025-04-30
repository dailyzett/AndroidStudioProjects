package com.example.bluromatic.workers

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bluromatic.DELAY_TIME_MILLIS
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import androidx.core.net.toUri
import androidx.work.workDataOf

private const val TAG = "BlurWorker"

class BlurWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {

        var resourceUri = inputData.getString(KEY_IMAGE_URI)
        var blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)

        makeStatusNotification(
            message = applicationContext.resources.getString(R.string.blurring_image),
            context = applicationContext,
        )

        return withContext(Dispatchers.IO) {

            delay(DELAY_TIME_MILLIS)

            return@withContext try {

                require(!resourceUri.isNullOrBlank()) {
                    val errorMessage = applicationContext.resources.getString(R.string.invalid_input_uri)
                    Log.e(TAG, errorMessage)
                    errorMessage
                }

                val resolver = applicationContext.contentResolver

                val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(resourceUri.toUri())
                )

                val output: Bitmap = blurBitmap(picture, blurLevel)

                val outputUrl: Uri = writeBitmapToFile(applicationContext, output)

//                makeStatusNotification(
//                    "Output is $outputUrl",
//                    applicationContext
//                )

                Log.i(
                    TAG,
                    outputUrl.toString()
                )

                var outputData = workDataOf(KEY_IMAGE_URI to outputUrl.toString())

                Result.success(outputData)
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