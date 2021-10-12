package com.akmere.travelling_app.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.R
import com.akmere.travelling_app.presentation.common.Constants.halfContentSize
import com.akmere.travelling_app.presentation.home.lists.OfferCategoryListing
import com.akmere.travelling_app.presentation.home.lists.PopularOfferListing
import com.akmere.travelling_app.presentation.home.lists.SuggestionListing

@Composable
@Preview
fun HomeContent() {
    Column(
        Modifier
            .background(MaterialTheme.colors.primary)
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
    ) {
        WelcomeMessage(
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth(halfContentSize)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            hint = "Vai pra onde?",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OfferCategoryListing()
        Spacer(modifier = Modifier.height(8.dp))
        PopularOfferListing()
        Spacer(modifier = Modifier.height(8.dp))
        SuggestionListing()
    }
}

data class PopularOffer(
    val title: String,
    val favoriteCount: String,
    @DrawableRes val imageResource: Int
)

data class Suggestion(val title: String, val country: String, @DrawableRes val imageResource: Int)

data class OfferCategory(val title: String, @DrawableRes val imageResource: Int)

val offerCategories = listOf(
    OfferCategory("Hotel", R.drawable.ic_outline_hotel_24),
    OfferCategory("Pacotes", R.drawable.ic_baseline_card_travel_24)
)

val popularOffers = listOf(
    PopularOffer("Hotel em Phuket", "1k", R.drawable.phuket_hotel),
    PopularOffer("Pacote Phuket", "2k", R.drawable.phuket_package),
    PopularOffer("Pacote Phuket", "2k", R.drawable.phuket_package),
)

val suggestions = listOf(
    Suggestion("Hotel em Phuket", "Tailândia", R.drawable.phuket_hotel),
    Suggestion("Pacote Phuket", "Tailândia", R.drawable.phuket_package),
    Suggestion("Pacote Phuket", "Tailândia", R.drawable.phuket_package),
)
