package com.akmere.travelling_app.data.repository

class ViewedOfferRepository {
    private val viewedOfferDatasource = mutableMapOf<String, Boolean>()

    fun addViewed(offerId: String, boolean: Boolean) {
        viewedOfferDatasource[offerId]?.let {
            viewedOfferDatasource[offerId] = boolean
        } ?: viewedOfferDatasource.put(offerId, boolean)
    }

    fun getOfferIds(): List<String> {
        return viewedOfferDatasource.map { it.key }
    }
}
