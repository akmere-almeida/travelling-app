package com.akmere.travelling_app.presentation.home.model

import androidx.annotation.DrawableRes

data class PopularOffer(
    val title: String,
    val favoriteCount: String,
    @DrawableRes val imageResource: Int
)
