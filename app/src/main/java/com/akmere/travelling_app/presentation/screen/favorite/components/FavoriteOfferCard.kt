package com.akmere.travelling_app.presentation.screen.favorite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppShapes
import com.akmere.travelling_app.presentation.screen.favorite.model.FavoriteOffer

@Composable
fun FavoriteOfferCard(
    favoriteOffer: FavoriteOffer,
    modifier: Modifier,
    onFavoriteOfferSelected: (FavoriteOffer) -> Unit
) {
    Card(
        shape = AppShapes.medium,
        modifier = modifier
            .clickable(onClick = { onFavoriteOfferSelected(favoriteOffer) })
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                bitmap = favoriteOffer.imageResource.asImageBitmap(),
                contentDescription = favoriteOffer.title,
                alpha = 0.8f,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = favoriteOffer.title,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}
