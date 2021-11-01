package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.common.AppDimensions.homeSearchBarSearchButtonWeight
import com.akmere.travelling_app.presentation.common.AppDimensions.homeSearchBarSearchTextWeight
import com.akmere.travelling_app.presentation.common.AppShapes

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onSearchClicked: () -> Unit,
    onTextClicked: () -> Unit,
) {
    Card(modifier, shape = AppShapes.medium) {
        Row(
            Modifier
                .clickable {
                    onTextClicked()
                }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                maxLines = 1,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(homeSearchBarSearchTextWeight)
            )
            IconButton(
                modifier = Modifier
                    .background(MaterialTheme.colors.secondaryVariant)
                    .weight(homeSearchBarSearchButtonWeight),
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}
