package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.repository.FavoriteRepository

class IsOfferFavorite(private val favoriteRepository: FavoriteRepository) {
    fun execute(offerId: String): Boolean {
        return favoriteRepository.isFavorite(offerId)
    }
}
