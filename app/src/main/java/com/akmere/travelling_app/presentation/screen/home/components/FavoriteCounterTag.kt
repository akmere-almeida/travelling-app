package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppColorCodes
import com.akmere.travelling_app.presentation.common.AppShapes

@Composable
fun FavoriteCounterTag(favoriteCount: String, modifier: Modifier) {
    Card(
        shape = AppShapes.large,
        modifier = modifier,
        contentColor = Color(AppColorCodes.Gray),
        backgroundColor = Color(AppColorCodes.Gray)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 2.dp, end = 2.dp)
        ) {
            Icon(
                Icons.Filled.Favorite,
                "Favoritar",
                tint = Color(AppColorCodes.Red),
                modifier = Modifier
                    .padding(4.dp),
            )
            Text(
                text = favoriteCount,
                maxLines = 1,
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.primary,
            )
        }
    }
}
