package com.akmere.travelling_app.presentation.screen.offer_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.components.BackButton
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferImage

@Composable
fun OfferDetailsImageBox(
    image: OfferImage,
    onImageSelected: () -> Unit,
    onBackPressed: () -> Unit,
    onShareClicked: () -> Unit
) {
    Box {
        Image(
            bitmap = image.bitmap.asImageBitmap(),
            contentDescription = image.contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .height(250.dp)
                .clip(
                    RoundedCornerShape(bottomEndPercent = 15, bottomStartPercent = 15)
                )
                .clickable {
                    onImageSelected()
                },
        )

        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 24.dp)
                .alpha(0.8f)
                .background(MaterialTheme.colors.primary), onBackPressed
        )

        ShareButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 24.dp)
                .alpha(0.8f)
                .background(MaterialTheme.colors.primary), onShareClicked
        )
    }
}

@Composable
fun ShareButton(modifier: Modifier, onShareButtonClicked: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = { onShareButtonClicked() },
    ) {
        Image(
            imageVector = Icons.Filled.Share,
            contentDescription = "Compartilhar",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
        )
    }
}