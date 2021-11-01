package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppDimensions.halfContentSize

@Composable
fun HomeTopAppBar() {
    WelcomeMessage(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(halfContentSize)
    )
}
