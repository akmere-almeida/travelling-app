package com.akmere.travelling_app.data.repository

import kotlin.random.Random
import kotlin.random.nextInt


class FavoriteRepository {
    private val favoriteOffersDatasource = mutableMapOf<String, Boolean>()
    private val offerFavoriteCountDatasource = mutableMapOf<String, Int>()

    fun addFavorite(id: String, boolean: Boolean) {
        favoriteOffersDatasource[id]?.let {
            favoriteOffersDatasource[id] = boolean
        } ?: favoriteOffersDatasource.put(id, boolean)
    }

    fun isFavorite(id: String): Boolean {
        return favoriteOffersDatasource[id] ?: false
    }

    fun getIds(): List<String> {
        return favoriteOffersDatasource.map { it.key }
    }

    fun getOfferFavoriteCount(id: String): Int {
        return offerFavoriteCountDatasource.getOrPut(id) {
            Random.nextInt(10..3000)
        }
    }
}
