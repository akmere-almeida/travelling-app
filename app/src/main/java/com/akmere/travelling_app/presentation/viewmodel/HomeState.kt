package com.akmere.travelling_app.presentation.viewmodel

import com.akmere.travelling_app.presentation.home.model.PopularOffer

data class HomeState(val popularOffers: List<PopularOffer>, val userLocation: String)
