package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onSearchClicked: () -> Unit,
    onTextClicked: () -> Unit,
) {
    Card(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                Modifier
                    .weight(3f)
                    .clickable {
                        onTextClicked()
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = text,
                    maxLines = 1,
                    color = Color.LightGray,
                )
            }
            IconButton(
                modifier = Modifier.background(MaterialTheme.colors.secondaryVariant),
                onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}
