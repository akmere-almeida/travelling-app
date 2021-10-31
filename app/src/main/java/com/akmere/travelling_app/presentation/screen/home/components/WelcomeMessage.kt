package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeMessage(modifier: Modifier) {
    Text(
        text = "Venha viajar com a gente !",
        fontSize = 28.sp,
        modifier = modifier,
        style = TextStyle(
            color = MaterialTheme.colors.secondaryVariant,
            fontWeight = FontWeight.SemiBold
        )
    )
}
