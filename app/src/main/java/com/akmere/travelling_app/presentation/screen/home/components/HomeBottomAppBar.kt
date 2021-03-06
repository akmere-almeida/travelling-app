package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun HomeBottomAppBar(onFavoriteSelected: () -> Unit) {
    BottomAppBar(
        content = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.primary) {
                BottomNavigationItem(
                    icon = {
                        Icon(Icons.Filled.Home, "Início")
                    },
                    label = { Text(text = "Home") },
                    selected = true,
                    onClick = {
                    },
                    alwaysShowLabel = true,
                    selectedContentColor = MaterialTheme.colors.secondaryVariant,
                    unselectedContentColor = Color.LightGray
                )

                BottomNavigationItem(
                    icon = {
                        Icon(Icons.Filled.Favorite, "Favoritos")
                    },
                    label = { Text(text = "Favoritos") },
                    selected = false,
                    onClick = {
                        onFavoriteSelected()
                    },
                    alwaysShowLabel = true,
                    selectedContentColor = MaterialTheme.colors.secondaryVariant,
                    unselectedContentColor = Color.LightGray
                )

                BottomNavigationItem(
                    icon = {
                        Icon(Icons.Filled.Place, "Minhas viagems")
                    },
                    label = { Text(text = "Minhas Viagems") },
                    selected = false,
                    onClick = {
//                                        result.value = "Upload icon clicked"
//                                        selectedItem.value = "upload"
                    },
                    alwaysShowLabel = true,
                    selectedContentColor = MaterialTheme.colors.secondaryVariant,
                    unselectedContentColor = Color.LightGray
                )
            }
        }
    )
}
