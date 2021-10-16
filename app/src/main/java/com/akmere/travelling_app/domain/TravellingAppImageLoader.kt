package com.akmere.travelling_app.domain

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import coil.ImageLoader

class TravellingAppImageLoader(private val imageLoader: ImageLoader) {
    suspend fun loadFromNetwork(imageUrl: String): ImageBitmap {
        TODO()
    }

    suspend fun loadFromNetwork(imageUrls: List<String>): List<Bitmap> {
        TODO()
    }
}
