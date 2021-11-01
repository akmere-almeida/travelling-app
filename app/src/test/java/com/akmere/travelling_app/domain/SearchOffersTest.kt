package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.DataFixtures
import com.akmere.travelling_app.data.repository.FavoriteRepository
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.domain.DomainFixtures.offersOrderedDescendingByFavoriteCount
import com.akmere.travelling_app.presentation.model.FilterOptions
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchOffersTest {
    private val offerService = mockk<OfferService>()
    private val favoriteRepository = mockk<FavoriteRepository>()

    private lateinit var searchOffers: SearchOffers

    @Before
    fun setup() {
        searchOffers = SearchOffers(offerService, favoriteRepository, null)
    }

    @Test
    fun `offers should be sorted by descending favorite count`() = runBlocking {
        coEvery {
            offerService.getOffers(any(), any(), any(), any())
        } returns DataFixtures.offersData

        every { favoriteRepository.getOfferFavoriteCount(any()) } returns 100 andThen
            200 andThen
            150 andThen
            149

        val expectedOffers = offersOrderedDescendingByFavoriteCount
        val resultOffers = searchOffers.execute(FilterOptions.empty)

        assertEquals(expectedOffers, resultOffers)
    }
}
