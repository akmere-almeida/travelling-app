package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.data.repository.exceptions.OfferParseException
import com.akmere.travelling_app.data.repository.exceptions.UnexpectedLoadException

/**
 * Interface usada para comunicação com API de Ofertas
 */
interface OfferRepository<T> {
    /**
     *
     * Carrega as informações de Ofertas
     *
     * @param imagesPerOffer quantidade de imagens da galeria de cada Oferta
     * @param searchTerms lista de locais relacionada a Ofertas (ex: Rio de Janeiro, Paraty)
     *
     * @return lista de tipo [T] de Oferta
     *
     * @throws OfferParseException
     * @throws UnexpectedLoadException
     */
    @Throws(OfferParseException::class, UnexpectedLoadException::class)
    suspend fun getOffers(imagesPerOffer: Int, searchTerms: List<String>): List<T>
}
