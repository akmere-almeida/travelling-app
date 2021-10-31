package com.akmere.travelling_app.domain.model

data class OfferDetails(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val favoriteCount: Int,
    val mainImage: ImageItem,
    val gallery: List<ImageItem>,
    val lat: Double,
    val lon: Double
)
