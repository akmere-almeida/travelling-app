package com.akmere.travelling_app.common

import com.akmere.travelling_app.data.model.OfferData
import com.akmere.travelling_app.domain.model.ImageItem
import com.akmere.travelling_app.domain.model.TravelOffer

object OfferExtensions {
    fun OfferData.toTravelOffer(favoriteCount: Int): TravelOffer {
        val address =
            addressData.country ?: addressData.state ?: ""

        val image =
            ImageItem(
                galleryData.images.first().url,
                galleryData.images.first().description
            )

        return TravelOffer(id, name, address, image, favoriteCount)
    }
}
