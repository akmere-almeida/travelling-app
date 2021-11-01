package com.akmere.travelling_app.presentation.screen.offer_details.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.screen.offer_details.components.ImageGalleryCardItem
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferImage

@Composable
fun ImageGalleryListing(
    gallery: List<OfferImage>,
    modifier: Modifier,
    onImageSelected: () -> Unit
) {
    Column(modifier) {
        Text(
            text = "Imagens",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondary,
        )

        if (gallery.isEmpty())
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Não há fotos disponíveis.",
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondaryVariant,
            )
        else {
            LazyRow {
                items(gallery) {
                    ImageGalleryCardItem(it, onImageSelected)
                }
            }
        }
    }
}
