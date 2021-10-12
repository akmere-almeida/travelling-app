package com.akmere.travelling_app.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akmere.travelling_app.presentation.home.components.HomeBottomAppBar
import com.akmere.travelling_app.presentation.home.components.HomeContent
import com.akmere.travelling_app.presentation.home.components.HomeTopAppBar

@Composable
@Preview
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopAppBar()
        },
        content = {
            HomeContent()
        },
        bottomBar = {
            HomeBottomAppBar()
        }
    )
}
