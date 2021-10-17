package com.akmere.travelling_app.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.akmere.travelling_app.presentation.UiState
import com.akmere.travelling_app.presentation.home.components.HomeBottomAppBar
import com.akmere.travelling_app.presentation.home.components.SuccessfulHomeContent
import com.akmere.travelling_app.presentation.home.components.HomeTopAppBar
import com.akmere.travelling_app.presentation.home.components.LoadingHomeContent
import com.akmere.travelling_app.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Scaffold(
        topBar = {
            HomeTopAppBar()
        },
        content = {
            homeViewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Error -> TODO()
                    UiState.Loading -> LoadingHomeContent()
                    is UiState.Success -> SuccessfulHomeContent(uiState.data)
                }
            }
        },
        bottomBar = {
            HomeBottomAppBar()
        }
    )
}
