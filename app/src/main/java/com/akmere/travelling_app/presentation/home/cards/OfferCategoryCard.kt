package com.akmere.travelling_app.presentation.home.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.home.components.OfferCategory

@Composable
fun OfferCategoryCard(offerCategory: OfferCategory, modifier: Modifier) {
    Card(
        modifier = modifier.clickable(onClick = { /* Ignoring onClick */ }),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
        ) {
            Image(
                painter = painterResource(id = offerCategory.imageResource),
                contentDescription = offerCategory.title,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondaryVariant)
            )
            Text(
                text = offerCategory.title,
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}
