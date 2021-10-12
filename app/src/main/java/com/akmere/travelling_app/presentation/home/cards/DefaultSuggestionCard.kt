package com.akmere.travelling_app.presentation.home.cards

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.home.components.Suggestion

@Composable
fun DefaultSuggestionCard(suggestion: Suggestion) {
    SuggestionCard(
        suggestion,
        Modifier
            .padding(end = 16.dp)
            .width(160.dp)
            .height(80.dp)
    )
}
