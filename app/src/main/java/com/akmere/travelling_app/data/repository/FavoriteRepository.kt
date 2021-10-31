package com.akmere.travelling_app.data.repository


class FavoriteRepository {
    private val favoriteDatasource = mutableMapOf<String, Boolean>()

    fun addFavorite(id: String, boolean: Boolean) {
        favoriteDatasource[id]?.let {
            favoriteDatasource[id] = boolean
        } ?: favoriteDatasource.put(id, boolean)
    }

    fun isFavorite(id: String): Boolean {
        return favoriteDatasource[id] ?: false
    }

    fun getIds(): List<String> {
        return favoriteDatasource.map { it.key }
    }
}
