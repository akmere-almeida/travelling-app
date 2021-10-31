package com.akmere.travelling_app.data.repository

import junit.framework.Assert.*
import org.junit.Test

class FavoriteRepositoryTest {

    private val favoriteRepository = FavoriteRepository()

    @Test
    fun `should toggle favorite value when update favorite offer`() {
        val offerId = "offer1"
        favoriteRepository.updateFavoriteOffer(offerId, true)
        var favoriteValue = favoriteRepository.isOfferFavorite(offerId)
        assertTrue(favoriteValue)

        favoriteRepository.updateFavoriteOffer(offerId, false)
        favoriteValue = favoriteRepository.isOfferFavorite(offerId)
        assertFalse(favoriteValue)
    }


    @Test
    fun `should update favorite count when toggle favorite offer`() {
        val offerId = "offer1"
        var expectedResult = favoriteRepository.getOfferFavoriteCount(offerId)+1
        favoriteRepository.updateFavoriteOffer(offerId, true)
        var favoriteCount = favoriteRepository.getOfferFavoriteCount(offerId)
        assertEquals(expectedResult, favoriteCount)

        expectedResult = favoriteRepository.getOfferFavoriteCount(offerId)-1
        favoriteRepository.updateFavoriteOffer(offerId, false)
        favoriteCount = favoriteRepository.getOfferFavoriteCount(offerId)
        assertEquals(expectedResult, favoriteCount)
    }
}