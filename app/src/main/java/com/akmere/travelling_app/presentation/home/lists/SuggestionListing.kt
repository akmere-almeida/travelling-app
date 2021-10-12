package com.akmere.travelling_app.presentation.home.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.home.cards.DefaultSuggestionCard
import com.akmere.travelling_app.presentation.home.components.suggestions

@Composable
fun SuggestionListing() {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Sugestões",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondary
        )
        LazyRow(modifier = Modifier.padding(top = 16.dp)) {
            items(suggestions) {
                DefaultSuggestionCard(it)
            }
        }
    }
}
