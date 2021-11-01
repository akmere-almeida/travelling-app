package com.akmere.travelling_app.presentation.screen.favorite.listing

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.screen.favorite.components.FavoriteOfferCard
import com.akmere.travelling_app.presentation.screen.favorite.model.FavoriteOffer

@Composable
fun FavoriteOfferListing(
    favoriteOffers: List<FavoriteOffer>,
    modifier: Modifier,
    onFavoriteOfferSelected: (FavoriteOffer) -> Unit
) {
    LazyColumn(modifier) {
        items(favoriteOffers) {
            FavoriteOfferCard(
                it,
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(180.dp),
                onFavoriteOfferSelected
            )
        }
    }
}
