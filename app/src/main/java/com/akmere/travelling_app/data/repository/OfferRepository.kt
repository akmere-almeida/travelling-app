package com.akmere.travelling_app.data.repository

import android.util.Log
import com.akmere.travelling_app.GetHotelOfferQuery
import com.akmere.travelling_app.GetPackagerOfferQuery
import com.akmere.travelling_app.SearchOfferByIdsQuery
import com.akmere.travelling_app.SearchOfferBySuggestionQuery
import com.akmere.travelling_app.SearchOfferByTermQuery
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.common.model.OfferType
import com.akmere.travelling_app.data.model.OfferData
import com.akmere.travelling_app.data.model.OfferDetailsData
import com.akmere.travelling_app.data.model.ProductType
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.data.service.exceptions.OfferDetailsNotFoundException
import com.akmere.travelling_app.data.service.exceptions.OfferParseException
import com.akmere.travelling_app.data.service.exceptions.UnexpectedLoadException
import com.akmere.travelling_app.data.toOfferData
import com.akmere.travelling_app.data.toOfferDetailsData
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloParseException

/**
 * Classe concreta que implementa [OfferService] usada para comunicação com API de Ofertas de Pacotes
 *
 *
 * Carrega as informações de Ofertas
 *
 * @param datasource cliente GraphQL para carregar informações da API
 *
 * @return uma lista de [OfferData] contendo informações de oferta dos pacotes
 *
 * @throws OfferParseException
 * @throws UnexpectedLoadException
 */
class OfferRepository(
    private val datasource: ApolloClient,
    private val logger: Logger? = null
) : OfferService {

    override suspend fun getOffers(
        ids: List<String>?,
        offerTypes: List<OfferType>,
        imagesPerOffer: Int,
        suggestion: String?,
        searchTerm: String?
    ): List<OfferData> {
        if (ids != null) {
            val query = SearchOfferByIdsQuery(ids, 1)
            return searchOffersById(query)
        }
        val productTypes = offerTypes.map {
            it.toProductType().value
        }

        return if (suggestion != null) {
            val query = SearchOfferBySuggestionQuery(
                productTypes,
                Input.optional(suggestion),
                imagesPerOffer
            )
            searchOffersBySuggestion(query)
        } else {
            val query = SearchOfferByTermQuery(
                productTypes,
                Input.optional(searchTerm),
                imagesPerOffer
            )
            searchOffersByTerm(query)
        }
    }

    private suspend fun searchOffersBySuggestion(
        query: SearchOfferBySuggestionQuery
    ): List<OfferData> {
        return kotlin.runCatching {
            val queryResult =
                datasource.query(
                    query
                ).await()
            queryResult.data?.search?.results?.map {
                it.toOfferData()
            }.orEmpty()
        }.onSuccess {
            logger?.log(TAG, Log.DEBUG, message = "Ofertas encontradas: $it}", throwable = null)
        }.onFailure {
            handleFailure(it)
        }.getOrThrow()
    }

    private suspend fun searchOffersByTerm(query: SearchOfferByTermQuery): List<OfferData> {
        return kotlin.runCatching {
            val queryResult =
                datasource.query(
                    query
                ).await()
            queryResult.data?.search?.results?.map {
                it.toOfferData()
            }.orEmpty()
        }.onSuccess {
            logger?.log(TAG, Log.DEBUG, message = "Ofertas encontradas: $it}", throwable = null)
        }.onFailure {
            handleFailure(it)
        }.getOrThrow()
    }

    private suspend fun searchOffersById(query: SearchOfferByIdsQuery): List<OfferData> {
        return kotlin.runCatching {
            val queryResult =
                datasource.query(
                    query
                ).await()
            queryResult.data?.search?.results?.map {
                it.toOfferData()
            }.orEmpty()
        }.onSuccess {
            logger?.log(TAG, Log.DEBUG, message = "Ofertas encontradas: $it}", throwable = null)
        }.onFailure {
            handleFailure(it)
        }.getOrThrow()
    }

    override suspend fun getOfferDetailsData(
        id: String,
        offerType: OfferType
    ): OfferDetailsData {
        return if (offerType == OfferType.HOTEL) {
            getHotelOfferDetails(id)
        } else {
            getPackageOfferDetails(id)
        }
    }

    private suspend fun getHotelOfferDetails(id: String): OfferDetailsData {
        return kotlin.runCatching {
            val results =
                datasource.query(GetHotelOfferQuery(id, IMAGES_PER_OFFER_DETAILS))
                    .await().data?.searchHotel?.results
            results?.firstOrNull()?.toOfferDetailsData() ?: throw OfferDetailsNotFoundException()
        }.onSuccess {
            logger?.log(TAG, Log.DEBUG, message = "Oferta encontrada: $it}", throwable = null)
        }.onFailure { handleFailure(it) }.getOrThrow()
    }

    private suspend fun getPackageOfferDetails(id: String): OfferDetailsData {

        return kotlin.runCatching {
            val results =
                datasource.query(GetPackagerOfferQuery(id, IMAGES_PER_OFFER_DETAILS))
                    .await().data?.searchPackage?.results
            results?.firstOrNull()?.toOfferDetailsData() ?: throw OfferDetailsNotFoundException()
        }.onSuccess {
            logger?.log(TAG, Log.DEBUG, message = "Oferta encontrada: $it}", throwable = null)
        }.onFailure { handleFailure(it) }.getOrThrow()
    }

    private fun handleFailure(error: Throwable) {
        logger?.log(TAG, Log.DEBUG, message = error.message, throwable = error)

        when (error) {
            is ApolloParseException -> throw OfferParseException()
            else -> throw UnexpectedLoadException()
        }
    }

    private fun OfferType.toProductType(): ProductType {
        return when (this) {
            OfferType.HOTEL -> ProductType.HOTEL
            OfferType.PACKAGE -> ProductType.HOSPEDAGEM
        }
    }

    companion object {
        private const val TAG = "PackageOfferRepository"
        private const val IMAGES_PER_OFFER_DETAILS = 10
        const val DEFAULT_IMAGE_URL =
            "https://belem.com.br/images/noticias/2958/17122020112152_Foto-Arqui.jpg"
    }
}
