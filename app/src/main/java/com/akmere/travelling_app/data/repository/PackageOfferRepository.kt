package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.data.model.PackageOfferData

class PackageOfferRepository: OfferRepository<PackageOfferData> {
    override suspend fun getOffers(): List<PackageOfferData> {
        TODO()
    }
}

