package com.akmere.travelling_app.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import com.akmere.travelling_app.presentation.common.AppShapes

@Composable
fun BackButton(modifier: Modifier, onBackButtonPressed: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = { onBackButtonPressed() },
    ) {
        Image(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Voltar",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
        )
    }
}
