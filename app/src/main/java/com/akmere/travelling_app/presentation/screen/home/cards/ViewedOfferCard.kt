package com.akmere.travelling_app.presentation.screen.home.cards

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppColorCodes
import com.akmere.travelling_app.presentation.model.ViewedOffer

@Composable
fun ViewedOfferCard(modifier: Modifier, viewedOffer: ViewedOffer, onClick: (ViewedOffer) -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = { onClick(viewedOffer) }),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .fillMaxSize()
        ) {
            Image(
                bitmap = viewedOffer.image.asImageBitmap(),
                contentDescription = viewedOffer.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f),
                )
            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {

                Text(
                    text = viewedOffer.title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
                )
                Text(
                    text = viewedOffer.title,
                    style = MaterialTheme.typography.body2,
                    color = Color(AppColorCodes.Gray)
                )
            }
        }
    }
}
