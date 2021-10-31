package com.akmere.travelling_app.domain

import android.util.Log
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.common.OfferExtensions.toTravelOffer
import com.akmere.travelling_app.common.model.OfferType
import com.akmere.travelling_app.data.model.OfferData
import com.akmere.travelling_app.data.repository.FavoriteRepository
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.data.service.exceptions.OfferParseException
import com.akmere.travelling_app.data.service.exceptions.UnexpectedLoadException
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.domain.model.ImageItem
import com.akmere.travelling_app.domain.model.TravelOffer
import com.akmere.travelling_app.presentation.model.FilterOptions
import com.akmere.travelling_app.presentation.model.OfferCategory

/**
 * Caso de uso para buscar por ofertas
 *
 * @param offerService repositório responsável por carregar informações de ofertas
 *
 * @return uma lista de [OfferData] contendo informações de oferta dos pacotes
 *
 */
class SearchOffers(
    private val offerService: OfferService,
    private val favoriteRepository: FavoriteRepository,
    private val logger: Logger? = null
) {
    /**
     *
     * Executa a busca por ofertas
     *
     * @return uma lista de [TravelOffer] contendo informações de ofertas
     *
     * @throws SearchOffersNotFoundError
     */
    @Throws(SearchOffersNotFoundError::class)
    suspend fun execute(filterOptions: FilterOptions): List<TravelOffer> {
        return try {
            searchTravelOffers(filterOptions).map {
                val favoriteCount = favoriteRepository.getOfferFavoriteCount(it.id)
                it.toTravelOffer(favoriteCount)
            }.sortedByDescending { it.favoriteCount }.apply {
                if (isEmpty())
                    throw SearchOffersNotFoundError()
            }
        } catch (e: OfferParseException) {
            logger?.log(TAG, Log.DEBUG, message = e.message, throwable = e)
            throw SearchOffersNotFoundError()
        } catch (e: UnexpectedLoadException) {
            logger?.log(TAG, Log.DEBUG, message = e.message, throwable = e)
            throw SearchOffersNotFoundError()
        }
    }

    private fun OfferCategory?.toOfferType(): List<OfferType> {
        return when (this) {
            is OfferCategory.Package -> listOf(OfferType.PACKAGE)
            is OfferCategory.Hotel -> listOf(OfferType.HOTEL)
            else ->
                listOf(
                    OfferType.PACKAGE,
                    OfferType.HOTEL
                )
        }
    }

    private suspend fun searchTravelOffers(filterOptions: FilterOptions): List<OfferData> {
        return offerService.getOffers(
            imagesPerOffer = 1,
            suggestion = filterOptions.suggestionFilter,
            searchTerm = filterOptions.searchTerm,
            offerTypes = filterOptions.offerCategory.toOfferType()
        )
    }

    companion object {
        private const val TAG = "SearchOffers"
    }
}
