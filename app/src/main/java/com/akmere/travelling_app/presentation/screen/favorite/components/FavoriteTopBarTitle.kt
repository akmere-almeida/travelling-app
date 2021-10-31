package com.akmere.travelling_app.presentation.screen.favorite.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteTopBarTitle() {
    Text(
        text = "Favoritos",
        fontSize = 28.sp,
        style = TextStyle(
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.SemiBold
        )
    )
}
