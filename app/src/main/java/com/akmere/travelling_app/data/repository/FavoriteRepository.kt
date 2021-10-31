package com.akmere.travelling_app.data.repository

import kotlin.random.Random
import kotlin.random.nextInt


class FavoriteRepository {
    private val favoriteOffersDatasource = mutableMapOf<String, Boolean>()
    private val offerFavoriteCountDatasource = mutableMapOf<String, Int>()

    fun updateFavoriteOffer(offerId: String, favoriteValue: Boolean) {
        favoriteOffersDatasource[offerId]?.let {
            favoriteOffersDatasource[offerId] = favoriteValue
        } ?: favoriteOffersDatasource.put(offerId, favoriteValue)

        offerFavoriteCountDatasource[offerId]?.let {
            if (favoriteValue)
                offerFavoriteCountDatasource[offerId] = it + 1
            else
                offerFavoriteCountDatasource[offerId] = it - 1
        }
    }

    fun isOfferFavorite(offerId: String): Boolean {
        return favoriteOffersDatasource[offerId] ?: false
    }

    fun getFavoriteOfferIds(): List<String> {
        return favoriteOffersDatasource.map { it.key }
    }

    fun getOfferFavoriteCount(id: String): Int {
        return offerFavoriteCountDatasource.getOrPut(id) {
            Random.nextInt(10..3000)
        }
    }
}
