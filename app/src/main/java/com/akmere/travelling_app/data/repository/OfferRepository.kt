package com.akmere.travelling_app.data.repository

import android.util.Log
import com.akmere.travelling_app.*
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.common.model.OfferType
import com.akmere.travelling_app.data.model.*
import com.akmere.travelling_app.data.service.exceptions.OfferParseException
import com.akmere.travelling_app.data.service.exceptions.UnexpectedLoadException
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.data.service.exceptions.OfferDetailsNotFoundException
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

    private suspend fun searchOffersBySuggestion(query: SearchOfferBySuggestionQuery): List<OfferData> {
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
                datasource.query(GetHotelOfferQuery(id, 10))
                    .await().data?.searchHotel?.results
            results?.firstOrNull()?.toOfferDetailsData() ?: throw OfferDetailsNotFoundException()
        }.onSuccess {
            logger?.log(TAG, Log.DEBUG, message = "Oferta encontrada: $it}", throwable = null)
        }.onFailure { handleFailure(it) }.getOrThrow()
    }


    private suspend fun getPackageOfferDetails(id: String): OfferDetailsData {

        return kotlin.runCatching {
            val results =
                datasource.query(GetPackagerOfferQuery(id, 10))
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

    private fun SearchOfferBySuggestionQuery.Result.toOfferData(): OfferData {
        return OfferData(
            id = sku,
            name = name,
            description = description,
            isAvailable = isAvailable,
            addressData = AddressData(address?.city, null, address?.country, null, null),
            galleryData = gallery.map { image ->
                image.toImageData(name)
            }.run {
                GalleryData(this)
            }
        )
    }

    private fun SearchOfferByTermQuery.Result.toOfferData(): OfferData {
        return OfferData(
            id = sku,
            name = name,
            description = description,
            isAvailable = isAvailable,
            addressData = AddressData(address?.city, null, address?.country, null, null),
            galleryData = gallery.map { image ->
                image.toImageData(name)
            }.run {
                GalleryData(this)
            }
        )
    }

    private fun SearchOfferByIdsQuery.Result.toOfferData(): OfferData {
        return OfferData(
            id = sku,
            name = name,
            description = description,
            isAvailable = isAvailable,
            addressData = AddressData(address?.city, null, address?.country, null, null),
            galleryData = gallery.map { image ->
                image.toImageData(name)
            }.run {
                GalleryData(this)
            }
        )
    }

    private fun SearchOfferByTermQuery.Gallery.toImageData(defaultDescription: String): ImageData {
        return ImageData(url ?: DEFAULT_IMAGE_URL, description = description ?: defaultDescription)
    }

    private fun SearchOfferBySuggestionQuery.Gallery.toImageData(defaultDescription: String): ImageData {
        return ImageData(url ?: DEFAULT_IMAGE_URL, description = description ?: defaultDescription)
    }

    private fun GetHotelOfferQuery.Gallery.toImageData(defaultDescription: String): ImageData {
        return ImageData(url ?: DEFAULT_IMAGE_URL, description = description ?: defaultDescription)
    }

    private fun GetPackagerOfferQuery.Gallery.toImageData(defaultDescription: String): ImageData {
        return ImageData(url ?: DEFAULT_IMAGE_URL, description = description ?: defaultDescription)
    }

    private fun SearchOfferByIdsQuery.Gallery.toImageData(defaultDescription: String): ImageData {
        return ImageData(url ?: DEFAULT_IMAGE_URL, description = description ?: defaultDescription)
    }

    private fun GetHotelOfferQuery.Result.toOfferDetailsData(): OfferDetailsData {
        val addressData = AddressData(
            address.city,
            address.state,
            address.country,
            address.geoLocation?.lat,
            address.geoLocation?.lon
        )

        val galleryData = GalleryData(gallery.map { it.toImageData(name) })

        return OfferDetailsData(
            id = sku,
            name = name,
            description = description,
            address = addressData,
            galleryData = galleryData
        )
    }

    private fun GetPackagerOfferQuery.Result.toOfferDetailsData(): OfferDetailsData {
        val addressData = AddressData(
            address.city,
            address.state,
            address.country,
            address.geoLocation?.lat,
            address.geoLocation?.lon
        )

        val galleryData = GalleryData(gallery.map { it.toImageData(name) })

        return OfferDetailsData(
            id = sku,
            name = name,
            description = description,
            address = addressData,
            galleryData = galleryData
        )
    }

    private fun OfferType.toProductType(): ProductType {
        return when (this) {
            OfferType.HOTEL -> ProductType.HOTEL
            OfferType.PACKAGE -> ProductType.HOSPEDAGEM
        }
    }

    companion object {
        private const val TAG = "PackageOfferRepository"
        const val DEFAULT_IMAGE_URL =
            "https://belem.com.br/images/noticias/2958/17122020112152_Foto-Arqui.jpg"
    }
}
