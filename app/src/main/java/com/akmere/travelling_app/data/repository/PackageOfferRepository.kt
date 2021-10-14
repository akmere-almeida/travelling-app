package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.SearchPackageQuery
import com.akmere.travelling_app.data.model.AddressData
import com.akmere.travelling_app.data.model.GalleryData
import com.akmere.travelling_app.data.model.ImageData
import com.akmere.travelling_app.data.model.PackageOfferData
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import okio.IOException

class PackageOfferRepository(private val datasource: ApolloClient) :
    OfferRepository<PackageOfferData> {

    override suspend fun getOffers(
        imagesPerOffer: Int,
        searchTerms: List<String>
    ): List<PackageOfferData> {
        val queryResult = datasource.query(SearchPackageQuery(searchTerms, imagesPerOffer)).await()
        return queryResult.data?.searchPackage?.results?.map {
            it.toPackageOfferData()
        } ?: throw IOException()
    }
}

fun SearchPackageQuery.Result.toPackageOfferData(): PackageOfferData {
    return PackageOfferData(
        name = name,
        description = description,
        isAvailable = isAvailable,
        addressData = AddressData(address.city, address.country),
        galleryData = gallery.map { image ->
            image.toImageData()
        }.run {
            GalleryData(this)
        }
    )
}

fun SearchPackageQuery.Gallery.toImageData(): ImageData {
    return ImageData(url, description = description)
}
