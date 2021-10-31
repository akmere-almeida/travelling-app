package com.akmere.travelling_app.data.model

data class OfferDetailsData(
    val id: String,
    val name: String,
    val description: String,
    val address: AddressData,
    val galleryData: GalleryData
)
