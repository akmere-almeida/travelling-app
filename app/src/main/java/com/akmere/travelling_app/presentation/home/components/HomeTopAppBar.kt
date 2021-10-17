package com.akmere.travelling_app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.UiState
import com.akmere.travelling_app.presentation.home.model.PopularOffer
import com.akmere.travelling_app.presentation.viewmodel.HomeState

@Composable
fun HomeTopAppBar(uiState: UiState<HomeState>) {
    val location = when(uiState){
        is UiState.Success -> uiState.data.userLocation
        else -> ""
    }
    LocationTitle(location)
}

@Composable
fun LocationTitle(location: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Outlined.LocationOn,
            modifier = Modifier.padding(end = 8.dp),
            contentDescription = "Sua localização",
            tint = MaterialTheme.colors.secondaryVariant
        )
        Text(
            location,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.secondary
            )
        )
    }
}
