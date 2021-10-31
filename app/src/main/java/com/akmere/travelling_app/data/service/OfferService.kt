package com.akmere.travelling_app.data.service

import com.akmere.travelling_app.common.model.OfferType
import com.akmere.travelling_app.data.model.OfferData
import com.akmere.travelling_app.data.model.OfferDetailsData
import com.akmere.travelling_app.data.service.exceptions.OfferParseException
import com.akmere.travelling_app.data.service.exceptions.UnexpectedLoadException

/**
 * Interface usada para comunicação com API de ofertas (pacote de viagens e hospedagem)
 */
interface OfferService {
    /**
     *
     * Carrega as informações de ofertas na API
     *
     * @param ids identificadores de ofertas (ex: LGPKG-1095255-0, HT-ZO3E-0-0-0-0-0-0-0-0-0)
     * @param offerTypes tipos de oferta (ex: [OfferType.HOTEL], [OfferType.PACKAGE])
     * @param imagesPerOffer quantidade de imagens por oferta
     * @param searchTerm termo utilizado para busca de ofertas (ex: Rio de Janeiro, Paraty)
     *
     * @return uma lista de [OfferData] contendo informações do oferta
     *
     * @throws OfferParseException
     * @throws UnexpectedLoadException
     */
    @Throws(OfferParseException::class, UnexpectedLoadException::class)
    suspend fun getOffers(
        ids: List<String>? = null,
        offerTypes: List<OfferType> = emptyList(),
        imagesPerOffer: Int = 1,
        suggestion: String? = null,
        searchTerm: String? = null,
    ): List<OfferData>

    /**
     *
     * Carrega os detalhes de um oferta
     *
     * @param id identificador de oferta (ex: LGPKG-1095255-0, HT-ZO3E-0-0-0-0-0-0-0-0-0)
     * @param offerType tipo de oferta (ex: [OfferType.HOTEL], [OfferType.PACKAGE])
     *
     * @return [OfferDetailsData] contendo detalhamento do oferta
     *
     * @throws OfferParseException
     * @throws UnexpectedLoadException
     */
    suspend fun getOfferDetailsData(id: String, offerType: OfferType): OfferDetailsData
}
