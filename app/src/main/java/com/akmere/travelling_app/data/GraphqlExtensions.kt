package com.akmere.travelling_app.data

import com.akmere.travelling_app.GetHotelOfferQuery
import com.akmere.travelling_app.GetPackagerOfferQuery
import com.akmere.travelling_app.SearchOfferByIdsQuery
import com.akmere.travelling_app.SearchOfferBySuggestionQuery
import com.akmere.travelling_app.SearchOfferByTermQuery
import com.akmere.travelling_app.data.model.AddressData
import com.akmere.travelling_app.data.model.GalleryData
import com.akmere.travelling_app.data.model.ImageData
import com.akmere.travelling_app.data.model.OfferData
import com.akmere.travelling_app.data.model.OfferDetailsData
import com.akmere.travelling_app.data.repository.OfferRepository

fun SearchOfferBySuggestionQuery.Result.toOfferData(): OfferData {
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

fun SearchOfferByTermQuery.Result.toOfferData(): OfferData {
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

fun SearchOfferByIdsQuery.Result.toOfferData(): OfferData {
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

fun SearchOfferByTermQuery.Gallery.toImageData(defaultDescription: String): ImageData {
    return ImageData(
        url ?: OfferRepository.DEFAULT_IMAGE_URL,
        description = description ?: defaultDescription
    )
}

fun SearchOfferBySuggestionQuery.Gallery.toImageData(defaultDescription: String): ImageData {
    return ImageData(
        url ?: OfferRepository.DEFAULT_IMAGE_URL,
        description = description ?: defaultDescription
    )
}

fun GetHotelOfferQuery.Gallery.toImageData(defaultDescription: String): ImageData {
    return ImageData(
        url ?: OfferRepository.DEFAULT_IMAGE_URL,
        description = description ?: defaultDescription
    )
}

fun GetPackagerOfferQuery.Gallery.toImageData(defaultDescription: String): ImageData {
    return ImageData(
        url ?: OfferRepository.DEFAULT_IMAGE_URL,
        description = description ?: defaultDescription
    )
}

fun SearchOfferByIdsQuery.Gallery.toImageData(defaultDescription: String): ImageData {
    return ImageData(
        url ?: OfferRepository.DEFAULT_IMAGE_URL,
        description = description ?: defaultDescription
    )
}

fun GetHotelOfferQuery.Result.toOfferDetailsData(): OfferDetailsData {
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

fun GetPackagerOfferQuery.Result.toOfferDetailsData(): OfferDetailsData {
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
