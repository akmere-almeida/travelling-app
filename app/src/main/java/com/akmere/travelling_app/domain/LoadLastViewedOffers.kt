package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.repository.FavoriteRepository
import com.akmere.travelling_app.data.repository.ViewedOfferRepository
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.domain.errors.SuggestionFiltersNotFoundError
import com.akmere.travelling_app.domain.model.ImageItem
import com.akmere.travelling_app.domain.model.TravelOffer

class LoadLastViewedOffers(
    private val viewedOfferRepository: ViewedOfferRepository,
    private val favoriteRepository: FavoriteRepository,
    private val offerService: OfferService
) {
    suspend fun execute(): List<TravelOffer> {
        return runCatching {
            val viewedOfferIds = viewedOfferRepository.getOfferIds()
            val packageOfferIds = viewedOfferIds.filter { it.startsWith("LGPKG") }
            val hotelOfferIds = viewedOfferIds.filter { it.startsWith("HT") }

            val packageOffersRequest =
                offerService.getOffers(ids = packageOfferIds, imagesPerOffer = 1)
            val hotelOffersRequest = offerService.getOffers(ids = hotelOfferIds, imagesPerOffer = 1)

            packageOffersRequest.union(hotelOffersRequest).apply {
                if (isEmpty())
                    throw SuggestionFiltersNotFoundError()
            }.map { offerData ->
                val favoriteCount = favoriteRepository.getOfferFavoriteCount(offerData.id)
                val address =
                    offerData.addressData.country ?: offerData.addressData.state ?: ""

                val image =
                    ImageItem(
                        offerData.galleryData.images.first().url,
                        offerData.galleryData.images.first().description
                    )

                TravelOffer(
                    offerData.id,
                    offerData.addressData.city ?: "",
                    address,
                    image,
                    favoriteCount
                )
            }
        }.onFailure {
            if (it !is SuggestionFiltersNotFoundError)
                throw UnknownError()
        }.getOrThrow()
    }
}
