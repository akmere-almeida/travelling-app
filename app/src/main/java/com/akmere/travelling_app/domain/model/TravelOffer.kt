package com.akmere.travelling_app.domain.model

data class TravelOffer(
    val id: String,
    val name: String,
    val address: String,
    val image: ImageItem,
    val favoriteCount: Int
)
