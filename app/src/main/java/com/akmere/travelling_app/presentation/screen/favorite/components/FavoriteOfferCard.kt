package com.akmere.travelling_app.presentation.screen.favorite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.screen.favorite.model.FavoriteOffer


@Composable
fun FavoriteOfferCard(
    favoriteOffer: FavoriteOffer,
    modifier: Modifier,
    imageBitmap: ImageBitmap,
    onFavoriteOfferSelected: (FavoriteOffer) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .clickable(onClick = { onFavoriteOfferSelected(favoriteOffer) })
    ) {
        Box {
            Image(
                bitmap = imageBitmap,
                contentDescription = favoriteOffer.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),
                alpha = 0.8f
            )

            Text(
                text = favoriteOffer.title,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}
