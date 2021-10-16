package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.SearchPackageQuery
import com.akmere.travelling_app.data.model.AddressData
import com.akmere.travelling_app.data.model.GalleryData
import com.akmere.travelling_app.data.model.ImageData
import com.akmere.travelling_app.data.model.PackageOfferData
import com.akmere.travelling_app.data.repository.exceptions.OfferParseException
import com.akmere.travelling_app.data.repository.exceptions.UnexpectedLoadException
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloParseException

/**
 * Interface usada para comunicação com API de Ofertas de Pacotes
 *
 *
 * Carrega as informações de Ofertas
 *
 * @param datasource cliente GraphQL para carregar informações da API
 *
 * @return uma lista de [PackageOfferData] contendo informações de oferta dos pacotes
 *
 * @throws OfferParseException
 * @throws UnexpectedLoadException
 */
class PackageOfferRepository(private val datasource: ApolloClient) :
    OfferRepository<PackageOfferData> {

    override suspend fun getOffers(
        imagesPerOffer: Int,
        searchTerms: List<String>
    ): List<PackageOfferData> {
        try {
            val queryResult =
                datasource.query(SearchPackageQuery(searchTerms, imagesPerOffer)).await()
            try {
                return queryResult.data?.searchPackage?.results?.map {
                    it.toPackageOfferData()
                } ?: throw OfferParseException()
            } catch (e: ApolloParseException) {
                throw OfferParseException()
            }
        } catch (e: Exception) {
            throw UnexpectedLoadException()
        }
    }

    private fun SearchPackageQuery.Result.toPackageOfferData(): PackageOfferData {
        return PackageOfferData(
            name = name,
            description = description,
            isAvailable = isAvailable,
            addressData = AddressData(address.city, address.country),
            galleryData = gallery.map { image ->
                image.toImageData(name)
            }.run {
                GalleryData(this)
            }
        )
    }

    private fun SearchPackageQuery.Gallery.toImageData(defaultDescription: String): ImageData {
        return ImageData(url, description = description ?: defaultDescription)
    }
}
