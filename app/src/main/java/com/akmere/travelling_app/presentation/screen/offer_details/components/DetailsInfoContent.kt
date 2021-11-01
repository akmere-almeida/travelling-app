package com.akmere.travelling_app.presentation.screen.offer_details.components

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppColorCodes
import com.akmere.travelling_app.presentation.common.AppDimensions.offerDetailsInfoContentHeaderFavCountRowWeight
import com.akmere.travelling_app.presentation.common.AppDimensions.offerDetailsInfoContentHeaderTitleWeight
import com.akmere.travelling_app.presentation.common.AppShapes
import com.akmere.travelling_app.presentation.screen.offer_details.animation.FavoriteCount
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferDetails


@ExperimentalAnimationApi
@Composable
fun DetailsInfoContent(
    offerDetails: OfferDetails,
    modifier: Modifier,
    onLocationClicked: (Uri) -> Unit
) {
    val aboutScrollState = rememberScrollState(0)

    Column(modifier) {
        DetailsInfoContentHeader(offerDetails)
        DetailsInfoLocation(onLocationClicked, offerDetails)
        DetailsInfoAbout(offerDetails, aboutScrollState)
    }
}

@Composable
private fun DetailsInfoAbout(
    offerDetails: OfferDetails,
    aboutScrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(AppShapes.small),
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

@Composable
private fun DetailsInfoLocation(
    onLocationClicked: (Uri) -> Unit,
    offerDetails: OfferDetails
) {
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
}

@ExperimentalAnimationApi
@Composable
private fun DetailsInfoContentHeader(offerDetails: OfferDetails) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            text = offerDetails.title,
            modifier = Modifier
                .weight(offerDetailsInfoContentHeaderTitleWeight)
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondary,
        )
        Row(
            modifier = Modifier.weight(offerDetailsInfoContentHeaderFavCountRowWeight),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Outlined.Favorite,
                modifier = Modifier.align(Alignment.CenterVertically),
                contentDescription = "Favoritos",
                tint = Color(AppColorCodes.Red)
            )
            FavoriteCount(
                favoriteCount = offerDetails.favoriteCount,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
