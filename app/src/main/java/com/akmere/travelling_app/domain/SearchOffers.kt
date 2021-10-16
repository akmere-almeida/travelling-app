package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.model.PackageOfferData
import com.akmere.travelling_app.data.repository.OfferRepository
import com.akmere.travelling_app.domain.model.TravelOffer

/**
 * Caso de uso para buscar por ofertas
 *
 * @param offerRepository repositório responsável por carregar informações de ofertas
 *
 * @return uma lista de [PackageOfferData] contendo informações de oferta dos pacotes
 *
 * @throws OffersNotFoundError
 */
class SearchOffers(private val offerRepository: OfferRepository<PackageOfferData>) {
    /**
     *
     * Executa a busca por ofertas
     *
     * @return uma lista de [TravelOffer] contendo informações de ofertas
     */
    suspend fun execute(): List<TravelOffer> {
        return offerRepository.getOffers(imagesPerOffer = 1, searchTerms = listOf("Rio")).map {
            it.toTravelOffer()
        }
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
}
