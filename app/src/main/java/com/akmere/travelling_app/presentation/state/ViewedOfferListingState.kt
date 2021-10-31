package com.akmere.travelling_app.presentation.state

import com.akmere.travelling_app.presentation.model.ViewedOffer

data class ViewedOfferListingState(
    val lastViewedOffers: List<ViewedOffer> = emptyList(),
)
