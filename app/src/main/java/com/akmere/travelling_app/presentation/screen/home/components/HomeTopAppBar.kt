package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.Constants

@Composable
fun HomeTopAppBar() {
    WelcomeMessage(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(Constants.halfContentSize)
    )
}

@Composable
fun LocationTitle(userAddress: String) {
    Row(
        Modifier
            .fillMaxWidth()
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
            userAddress,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.secondary
            )
        )
    }
}
