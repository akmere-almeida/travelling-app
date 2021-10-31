package com.akmere.travelling_app.presentation.model

import android.graphics.Bitmap

data class PopularOffer(
    val id: String,
    val title: String,
    val favoriteCount: String,
    val imageResource: Bitmap
)
