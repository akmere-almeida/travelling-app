package com.akmere.travelling_app.presentation.model

import android.graphics.Bitmap

data class ViewedOffer(
    val id: String,
    val title: String,
    val location: String,
    val image: Bitmap
)
