package com.akmere.travelling_app.presentation.state

import com.akmere.travelling_app.presentation.model.PopularOffer

data class PopularOfferListingState(
    val popularOffers: List<PopularOffer> = emptyList(),
)
