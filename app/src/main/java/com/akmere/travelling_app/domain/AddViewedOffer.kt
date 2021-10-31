package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.repository.ViewedOfferRepository

class AddViewedOffer(private val viewedOfferRepository: ViewedOfferRepository) {
    fun execute(offerId: String, viewed: Boolean) {
        viewedOfferRepository.addViewed(offerId, viewed)
    }
}
