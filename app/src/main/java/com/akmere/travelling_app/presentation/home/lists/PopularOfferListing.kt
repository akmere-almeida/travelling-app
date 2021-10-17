package com.akmere.travelling_app.presentation.home.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.home.cards.PopularOfferCard
import com.akmere.travelling_app.presentation.home.model.PopularOffer

@Composable
fun PopularOfferListing(popularOffersData: List<PopularOffer>) {
    val popularOffers = remember { mutableStateOf(popularOffersData) }
    Column {
        Text(
            text = "Destaques",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondary
        )
        LazyRow(modifier = Modifier.padding(top = 16.dp)) {
            items(popularOffers.value) {
                PopularOfferCard(it, Modifier.padding(end = 16.dp), it.imageResource.asImageBitmap())
            }
        }
    }
}
