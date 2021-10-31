package com.akmere.travelling_app.presentation.screen.offer_details.model

import android.net.Uri

data class OfferDetails(
    val id: String,
    val mainImage: OfferImage,
    val title: String,
    val location: String,
    val locationUri: Uri,
    val about: String,
    val gallery: OfferGallery,
    val favoriteCount: String,
    val isFavorite: Boolean,
)
