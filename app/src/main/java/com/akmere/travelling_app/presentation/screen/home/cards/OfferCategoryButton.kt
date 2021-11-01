package com.akmere.travelling_app.presentation.screen.home.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppShapes
import com.akmere.travelling_app.presentation.model.OfferCategory

@Composable
fun OfferCategoryButton(
    modifier: Modifier,
    offerCategory: OfferCategory,
    isSelected: Boolean,
    onCategorySelected: (OfferCategory) -> Unit,
) {
    val imageColor = MaterialTheme.colors.secondaryVariant
    var textColor = MaterialTheme.colors.secondary
    var bgColor = MaterialTheme.colors.primaryVariant

    if (isSelected) {
        textColor = MaterialTheme.colors.primary
        bgColor = MaterialTheme.colors.secondary
    }

    IconButton(
        modifier = modifier.background(color = bgColor, AppShapes.medium),
        onClick = { onCategorySelected(offerCategory) }
    ) {
        OfferCategoryButtonContent(
            offerCategory,
            textColor = textColor,
            imageColor = imageColor,
        )
    }
}

@Composable
private fun OfferCategoryButtonContent(
    offerCategory: OfferCategory,
    textColor: Color,
    imageColor: Color
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = offerCategory.imageResource),
            contentDescription = offerCategory.contentDescription,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(imageColor)
        )
        Text(
            text = offerCategory.contentDescription,
            style = MaterialTheme.typography.button,
            color = textColor
        )
    }
}
