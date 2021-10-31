package com.akmere.travelling_app.data.model

data class OfferData(
    val id: String,
    val name: String,
    val description: String,
    val isAvailable: Boolean,
    val addressData: AddressData,
    val galleryData: GalleryData
)
