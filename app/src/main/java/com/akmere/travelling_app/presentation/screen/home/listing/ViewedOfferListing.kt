package com.akmere.travelling_app.presentation.screen.home.listing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.model.ViewedOffer
import com.akmere.travelling_app.presentation.screen.home.cards.ViewedOfferCard

@Composable
fun ViewedOfferListing(viewedOffers: List<ViewedOffer>, onOfferSelected: (ViewedOffer) -> Unit) {
    LazyRow(modifier = Modifier.padding(top = 16.dp)) {
        items(viewedOffers) {
            ViewedOfferCard(
                Modifier
                    .padding(end = 16.dp)
                    .width(160.dp)
                    .height(80.dp),
                it,
                onOfferSelected
            )
        }
    }
}
