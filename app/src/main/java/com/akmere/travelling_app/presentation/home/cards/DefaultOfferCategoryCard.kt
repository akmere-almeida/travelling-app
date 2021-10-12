package com.akmere.travelling_app.presentation.home.cards

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.home.components.OfferCategory

@Composable
fun DefaultOfferCategoryCard(offerCategory: OfferCategory) {
    OfferCategoryCard(
        offerCategory,
        Modifier
            .padding(end = 16.dp)
            .width(104.dp)
            .height(48.dp)
    )
}
