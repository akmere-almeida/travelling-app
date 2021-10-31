package com.akmere.travelling_app.domain

import com.akmere.travelling_app.common.model.OfferType
import com.akmere.travelling_app.data.repository.FavoriteRepository
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.domain.model.ImageItem
import com.akmere.travelling_app.domain.model.OfferDetails
import java.security.InvalidParameterException

class LoadOfferDetails(
    private val offerRepository: OfferService,
    private val favoriteRepository: FavoriteRepository
) {
    suspend fun execute(id: String): OfferDetails {

        val productType = id.toOfferType()

        val offerDetailsData = offerRepository
            .getOfferDetailsData(
                id, productType ?: throw InvalidParameterException()
            )

        val mainImageItem =
            ImageItem(
                offerDetailsData.galleryData.images.first().url,
                offerDetailsData.galleryData.images.first().description
            )

        val gallery =
            offerDetailsData.galleryData.images.filter { it.description != mainImageItem.description }
                .map { imageData ->
                    ImageItem(imageData.url, imageData.description)
                }

        val address =
            offerDetailsData.address.city.plus(", ").plus(offerDetailsData.address.country)

        val favoriteCount = favoriteRepository.getOfferFavoriteCount(id)

        return OfferDetails(
            id = offerDetailsData.id,
            name = offerDetailsData.name,
            description = offerDetailsData.description,
            address = address,
            favoriteCount = favoriteCount,
            lat = offerDetailsData.address.lat ?: 0.0,
            lon = offerDetailsData.address.lon ?: 0.0,
            mainImage = mainImageItem,
            gallery = gallery
        )
    }

    private fun String.toOfferType(): OfferType? {
        return when {
            startsWith("HT") -> {
                OfferType.HOTEL
            }
            startsWith("LGPK") -> {
                OfferType.PACKAGE
            }
            else -> {
                null
            }
        }
    }
}
