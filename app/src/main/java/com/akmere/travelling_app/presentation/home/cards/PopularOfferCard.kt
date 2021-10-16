package com.akmere.travelling_app.presentation.home.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.Constants
import com.akmere.travelling_app.presentation.home.components.FavoriteCounterTag
import com.akmere.travelling_app.presentation.home.model.PopularOffer

@Composable
fun PopularOfferCard(popularOffer: PopularOffer, modifier: Modifier, painter: Painter) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .width(168.dp)
            .height(176.dp)
            .clickable(onClick = { /* Ignoring onClick */ })
    ) {
        Box {
            Image(
                painter = painter,
                contentDescription = popularOffer.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),
                alpha = 0.8f
            )
            FavoriteCounterTag(
                popularOffer.favoriteCount,
                Modifier
                    .align(Alignment.TopEnd)
                    .width(78.dp)
                    .height(38.dp)
                    .padding(6.dp)
                    .alpha(Constants.favoriteCounterTagAlpha)
            )
            Text(
                text = popularOffer.title,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}
