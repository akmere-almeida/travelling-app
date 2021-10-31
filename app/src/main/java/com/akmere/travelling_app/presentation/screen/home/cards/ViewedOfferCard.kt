package com.akmere.travelling_app.presentation.screen.home.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppColorCodes
import com.akmere.travelling_app.presentation.common.AppShapes
import com.akmere.travelling_app.presentation.model.ViewedOffer

@Composable
fun ViewedOfferCard(modifier: Modifier, viewedOffer: ViewedOffer, onClick: (ViewedOffer) -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = { onClick(viewedOffer) }),
        shape = AppShapes.medium,
    ) {
        Row {
            Image(
                bitmap = viewedOffer.image.asImageBitmap(),
                contentDescription = viewedOffer.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 4.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = viewedOffer.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold, ),
                    color = MaterialTheme.colors.secondary,
                )
                Text(
                    text = viewedOffer.location,
                    style = MaterialTheme.typography.body2,
                    color = Color(AppColorCodes.Gray)
                )
            }
        }
    }
}
