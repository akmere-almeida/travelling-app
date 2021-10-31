package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.repository.FavoriteRepository
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.domain.errors.SuggestionFiltersNotFoundError
import com.akmere.travelling_app.domain.model.ImageItem
import com.akmere.travelling_app.domain.model.TravelOffer

class LoadFavoriteOffers(
    private val favoriteRepository: FavoriteRepository,
    private val offerService: OfferService
) {
    suspend fun execute(): List<TravelOffer> {
        return runCatching {
            val favoriteOfferIds = favoriteRepository.getIds()
            val packageOfferIds = favoriteOfferIds.filter { it.startsWith("LGPKG") }
            val hotelOfferIds = favoriteOfferIds.filter { it.startsWith("HT") }

            val packageOffersRequest =
                offerService.getOffers(ids = packageOfferIds, imagesPerOffer = 1)
            val hotelOffersRequest = offerService.getOffers(ids = hotelOfferIds, imagesPerOffer = 1)

            packageOffersRequest.union(hotelOffersRequest).apply {
                if (isEmpty())
                    throw SuggestionFiltersNotFoundError()
            }.map { offerData ->
                val address =
                    offerData.addressData.city.plus(", ").plus(offerData.addressData.country)

                val image =
                    ImageItem(
                        offerData.galleryData.images.first().url,
                        offerData.galleryData.images.first().description
                    )

                TravelOffer(
                    offerData.id,
                    offerData.name,
                    address,
                    image,
                    10
                )
            }
        }.onFailure {
            if (it !is SuggestionFiltersNotFoundError)
                throw UnknownError()
        }.getOrThrow()
    }
}
