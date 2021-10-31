package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.repository.FavoriteRepository

class SaveFavoriteOffer(private val favoriteRepository: FavoriteRepository) {
    fun execute(offerId: String, favorite: Boolean) {
        favoriteRepository.updateFavoriteOffer(offerId, favorite)
    }
}
