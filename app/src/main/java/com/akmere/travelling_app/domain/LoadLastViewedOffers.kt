package com.akmere.travelling_app.domain

import com.akmere.travelling_app.common.OfferExtensions.toTravelOffer
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

            val packageOffersResult =
                offerService.getOffers(ids = packageOfferIds, imagesPerOffer = 1)
            val hotelOffersResult = offerService.getOffers(ids = hotelOfferIds, imagesPerOffer = 1)

            packageOffersResult.union(hotelOffersResult).apply {
                if (isEmpty())
                    throw SuggestionFiltersNotFoundError()
            }.map { offerData ->
                val favoriteCount = favoriteRepository.getOfferFavoriteCount(offerData.id)
                offerData.toTravelOffer(favoriteCount)
            }.sortedByDescending { viewedOfferIds.indexOf(it.id) }
        }.onFailure {
            if (it !is SuggestionFiltersNotFoundError)
                throw UnknownError()
        }.getOrThrow()
    }
}
