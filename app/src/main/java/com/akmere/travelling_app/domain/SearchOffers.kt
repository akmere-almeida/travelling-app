package com.akmere.travelling_app.domain

import android.util.Log
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.data.model.PackageOfferData
import com.akmere.travelling_app.data.repository.OfferRepository
import com.akmere.travelling_app.data.repository.exceptions.OfferParseException
import com.akmere.travelling_app.data.repository.exceptions.UnexpectedLoadException
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.domain.model.TravelOffer
import com.akmere.travelling_app.presentation.home.model.FilterOptions

/**
 * Caso de uso para buscar por ofertas
 *
 * @param packageOfferRepository repositório responsável por carregar informações de ofertas
 *
 * @return uma lista de [PackageOfferData] contendo informações de oferta dos pacotes
 *
 */
class SearchOffers(
    private val packageOfferRepository: OfferRepository<PackageOfferData>,
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
            searchPackageOffers(filterOptions)
        } catch (e: OfferParseException) {
            logger?.log(TAG, Log.DEBUG, message = e.message, throwable = e)
            throw SearchOffersNotFoundError()
        } catch (e: UnexpectedLoadException) {
            logger?.log(TAG, Log.DEBUG, message = e.message, throwable = e)
            throw SearchOffersNotFoundError()
        }
    }

    private suspend fun searchPackageOffers(filterOptions: FilterOptions) =
        packageOfferRepository.getOffers(
            imagesPerOffer = 1,
            searchTerms = listOf(filterOptions.city, filterOptions.state)
        ).map {
            it.toTravelOffer()
        }

    private fun PackageOfferData.toTravelOffer(): TravelOffer.PackageOffer {
        return TravelOffer.PackageOffer(
            name,
            addressData.city,
            addressData.country,
            galleryData.images.first().url,
            1
        )
    }

    companion object {
        private const val TAG = "SearchOffers"
    }
}
