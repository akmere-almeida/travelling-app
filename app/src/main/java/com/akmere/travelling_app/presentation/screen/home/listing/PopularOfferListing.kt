package com.akmere.travelling_app.presentation.screen.home.listing

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.model.PopularOffer
import com.akmere.travelling_app.presentation.screen.home.cards.PopularOfferCard

@Composable
fun PopularOfferListing(
    popularOffersData: List<PopularOffer>,
    onPopularOfferSelected: (PopularOffer) -> Unit
) {
    val popularOffers = remember { mutableStateOf(popularOffersData) }

    LazyRow(modifier = Modifier.padding(top = 16.dp)) {
        items(popularOffers.value) {
            PopularOfferCard(
                it,
                Modifier.padding(end = 16.dp),
                it.imageResource.asImageBitmap(),
                onPopularOfferSelected
            )
        }
    }
}
