package com.akmere.travelling_app.data

import com.akmere.travelling_app.data.model.AddressData
import com.akmere.travelling_app.data.model.GalleryData
import com.akmere.travelling_app.data.model.ImageData
import com.akmere.travelling_app.data.model.OfferData
import com.akmere.travelling_app.data.model.OfferDetailsData

object DataFixtures {

    val rioPackageOffer = OfferData(
        id = "LGPKG-1139339-0",
        name = "Pacote de Viagem - Rio de Janeiro - Carnaval 2022",
        isAvailable = true,
        description = "Hospedagem:&nbsp;em&nbsp;Rio de Janeiro.&nbsp;O&nbsp;Hurb",

        addressData = AddressData(
            city = "Rio de Janeiro",
            country = "Brasil",
            state = null,
            lat = null,
            lon = null
        ),
        galleryData = GalleryData(
            images = listOf(
                ImageData(
                    url = "https://thumbcdn-z.hotelurbano.net/gkkweJJ_9JccaeWKEN-2M0i7cmo=/or" +
                        "igxorig/center/middle/filters:quality(70)/https://s3.amazonaws." +
                        "com/legado-prod/prod/ofertas/imagens/2021/10/29/12/33/shutterstock" +
                        "_481240843.jpg",
                    description = "Pacote de Viagem - Rio de Janeiro - Carnaval 2022"
                )
            )
        )
    )

    val paratyPackageOffer = OfferData(
        id = "LGPKG-1044784-0",
        name = "Pacote Paraty",
        isAvailable = true,
        description = "O pacote&nbsp;inclui:\r\n\r\nA&eacute;reo:&nbsp;Passagem a&eacute;rea",
        addressData = AddressData(
            city = "Paraty",
            country = "Brasil",
            state = null,
            lat = null,
            lon = null
        ),
        galleryData = GalleryData(
            images = listOf(
                ImageData(
                    url = "https://thumbcdn-z.hotelurbano.net/aTml11sBrmGR5tqDa6G-jFoemP4=/" +
                        "origxorig/center/middle/filters:quality(70)/https://s3." +
                        "amazonaws.com/legado-prod/prod/ofertas/imagens/2021/07" +
                        "/22/16/26/paraty1.jpg",
                    description = "Pacote Paraty"
                )
            )
        )
    )

    val hotelOfferDetails = OfferDetailsData(
        id = "HT-F9DW-0-0-0-0-0-0-0-0-0",
        name = "Sheraton Grand Rio Hotel & Resort",
        description = "Situado entre a badalada Barra da Tijuca e a famosa praia de Ipanema",
        address = AddressData(
            city = "Rio de Janeiro",
            country = "Brasil",
            state = "Rio de Janeiro",
            lat = -22.9924024,
            lon = -43.2333255
        ),
        galleryData = GalleryData(
            images = listOf(
                ImageData(
                    url = "https://thumbcdn-z.hotelurbano.net/KXMpFDkJNZvI_oqdiAiv-D3HWWs=" +
                        "/origxorig/center/middle/filters:quality(70)/https://novo-hu" +
                        ".s3.amazonaws.com/reservas/ota/prod/hotel/3187/sheraton-rio" +
                        "-grand-hotel-e-resort-062_20200207102618.jpg",
                    description = ""
                )
            )
        )
    )

    val packageOfferDetails = OfferDetailsData(
        id = "LGPKG-1139339-0",
        name = "Pacote de Viagem - Rio de Janeiro - Carnaval 2022",
        description = "p><strong>Hospedagem:&nbsp;</strong>em&nbsp;<strong>Rio de Janeiro.&nbsp;",
        address = AddressData(
            city = "Rio de Janeiro",
            country = "Brasil",
            state = "Rio de Janeiro",
            lat = -22.920158299821324,
            lon = -43.4579963721119
        ),
        galleryData = GalleryData(
            images = listOf(
                ImageData(
                    url = "https://thumbcdn-z.hotelurbano.net/gkkweJJ_9JccaeWKEN-2M0" +
                        "i7cmo=/origxorig/center/middle/filters:quality(70)/htt" +
                        "ps://s3.amazonaws.com/legado-prod/prod/ofertas/imagens/20" +
                        "21/10/29/12/33/shutterstock_481240843.jpg",
                    description = "Pacote de Viagem - Rio de Janeiro - Carnaval 2022"
                )
            )
        )
    )

    val rioPackageOfferCopy = rioPackageOffer.copy(id = "LGPKG-1139339-3")

    val paratyPackageOfferCopy = paratyPackageOffer.copy(id = "LGPKG-1139339-2")

    val offersData = listOf(
        paratyPackageOffer,
        rioPackageOffer,
        paratyPackageOfferCopy,
        rioPackageOfferCopy
    )
}
