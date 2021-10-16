package com.akmere.travelling_app.domain.model

sealed class TravelOffer {
    data class PackageOffer(
        val name: String,
        val city: String,
        val country: String,
        val imageUrl: String,
        val favoriteCount: Int,
    ) : TravelOffer()
}
