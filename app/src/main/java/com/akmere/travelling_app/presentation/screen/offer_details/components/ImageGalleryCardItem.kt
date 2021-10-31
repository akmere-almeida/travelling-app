package com.akmere.travelling_app.presentation.screen.offer_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppShapes
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferImage

@Composable
fun ImageGalleryCardItem(offerImage: OfferImage, onImageSelected: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .width(94.dp)
            .height(76.dp)
            .padding(end = 16.dp)
            .clickable {
                onImageSelected()
            },
        shape = AppShapes.large
        ) {
        Image(
            bitmap = offerImage.bitmap.asImageBitmap(),
            contentDescription = offerImage.contentDescription,
            contentScale = ContentScale.FillHeight
        )
    }
}