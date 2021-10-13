package com.akmere.travelling_app.data.repository

interface OfferRepository<T> {
   suspend fun getOffers(): List<T>
}
