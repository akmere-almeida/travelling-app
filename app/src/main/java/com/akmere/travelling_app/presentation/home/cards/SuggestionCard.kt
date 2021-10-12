package com.akmere.travelling_app.presentation.home.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppColorCodes
import com.akmere.travelling_app.presentation.home.components.Suggestion

@Composable
fun SuggestionCard(suggestion: Suggestion, modifier: Modifier) {
    Card(
        modifier = modifier.clickable(onClick = { /* Ignoring onClick */ }),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(id = suggestion.imageResource),
                contentDescription = "Imagem de ${suggestion.title}, ${suggestion.country}",
                contentScale = ContentScale.Crop,
                alpha = 0.8f,
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = suggestion.title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
                )
                Text(
                    text = suggestion.country,
                    style = MaterialTheme.typography.body2,
                    color = Color(AppColorCodes.Gray)
                )
            }
        }
    }
}
