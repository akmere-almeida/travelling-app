package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.data.DataFixtures
import com.akmere.travelling_app.data.model.PackageOfferData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PackageOfferRepositoryTest {
    @Test
    fun `should retrieve a collection of package offer data when requested`() = runBlocking {
        val packageOfferRepository: OfferRepository<PackageOfferData> = PackageOfferRepository()

        val expectedCollection = listOf(
            DataFixtures.rioPackageOffer,
            DataFixtures.paratyPackageOffer
        )

        val result = packageOfferRepository.getOffers()

        assertEquals(expectedCollection, result)
    }
}
