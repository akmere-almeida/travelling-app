package com.akmere.travelling_app.presentation.home.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.home.components.PopularOffer

@Composable
fun DefaultPopularOfferCard(popularOffer: PopularOffer) {
    PopularOfferCard(popularOffer, Modifier.padding(end = 16.dp))
}
