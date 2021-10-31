package com.akmere.travelling_app.presentation.screen.offer_details.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferDetails


@Composable
fun DetailsInfoContent(
    offerDetails: OfferDetails,
    modifier: Modifier,
    onLocationClicked: (Uri) -> Unit
) {
    val aboutScrollState = rememberScrollState(0)

    Column(modifier) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = offerDetails.title,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.secondary,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Favoritos",
                    tint = Color.Yellow
                )
                Text(
                    text = offerDetails.favoriteCount,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colors.secondary,
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clickable {
                    onLocationClicked(offerDetails.locationUri)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {

            Icon(
                modifier = Modifier.padding(end = 8.dp),
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Localização",
                tint = MaterialTheme.colors.secondaryVariant
            )
            Text(
                text = offerDetails.location,
                style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.secondary,
            )
        }

        Column(
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text(
                text = "Sobre",
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.secondary,
            )

            Text(
                text = offerDetails.about,
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(aboutScrollState)
            )

        }
    }
}