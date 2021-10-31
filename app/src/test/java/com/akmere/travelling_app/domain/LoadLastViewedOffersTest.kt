package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.DataFixtures
import com.akmere.travelling_app.data.model.OfferData
import com.akmere.travelling_app.data.repository.FavoriteRepository
import com.akmere.travelling_app.data.repository.ViewedOfferRepository
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.domain.DomainFixtures.offersOrderedByViewedPosition
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoadLastViewedOffersTest {
    private val offerService = mockk<OfferService>()
    private val favoriteRepository = mockk<FavoriteRepository>()
    private val viewedOfferRepository = mockk<ViewedOfferRepository>()
    private lateinit var loadLastViewedOffers: LoadLastViewedOffers

    @Before
    fun setup() {
        loadLastViewedOffers =
            LoadLastViewedOffers(viewedOfferRepository, favoriteRepository, offerService)
    }

    @Test
    fun `offers should be ordered by latest viewed position`() = runBlocking {
        val viewedOfferIds = offersOrderedByViewedPosition.map { it.id }

        every { favoriteRepository.getOfferFavoriteCount(any()) } returns 10

        every { viewedOfferRepository.getOfferIds() } returns viewedOfferIds

        coEvery {
            offerService.getOffers(any(), any(), any(), any(), any())
        } returns DataFixtures.offersData andThen emptyList()

        val expectedOffers = offersOrderedByViewedPosition.reversed()
        val resultOffers = loadLastViewedOffers.execute()

        Assert.assertEquals(expectedOffers, resultOffers)
    }
}
