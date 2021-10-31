package com.akmere.travelling_app.presentation.screen.favorite.listing

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.screen.favorite.components.FavoriteOfferCard
import com.akmere.travelling_app.presentation.screen.favorite.model.FavoriteOffer

@Composable
fun FavoriteOfferListing(
    favoriteOffers: List<FavoriteOffer>,
    onFavoriteOfferSelected: (FavoriteOffer) -> Unit
) {
    LazyColumn {
        items(favoriteOffers) {
            FavoriteOfferCard(
                it,
                Modifier.padding(end = 16.dp),
                it.imageResource.asImageBitmap(),
                onFavoriteOfferSelected
            )
        }
    }
}
