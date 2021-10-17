package com.akmere.travelling_app.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.domain.errors.ImageLoadError

class TravellingAppImageLoader(
    private val imageLoader: ImageLoader,
    private val context: Context,
    private val logger: Logger? = null
) {
    suspend fun loadFromNetwork(imageUrl: String): Drawable {
        val request = requestFromImageUrl(imageUrl)
        return try {
            imageLoader.execute(request).drawable ?: throw ImageLoadError()
        } catch (e: Exception) {
            logger?.log(TAG, Log.INFO, message = e.message, throwable = e)
            throw ImageLoadError(e.message)
        }
    }

    suspend fun loadFromNetwork(imageUrls: List<String>): List<Drawable> {
        return imageUrls.map { loadFromNetwork(it) }
    }

    private fun requestFromImageUrl(imageUrl: String): ImageRequest {
        return ImageRequest.Builder(context).data(imageUrl).build()
    }

    companion object {
        private const val TAG = "TravellingAppImageLoader"
    }
}
