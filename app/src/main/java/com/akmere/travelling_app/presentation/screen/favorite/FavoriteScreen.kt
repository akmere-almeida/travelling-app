package com.akmere.travelling_app.presentation.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.akmere.travelling_app.dependencies.AppDependencies
import com.akmere.travelling_app.presentation.common.components.BackButton
import com.akmere.travelling_app.presentation.common.components.LoadingContent
import com.akmere.travelling_app.presentation.screen.favorite.components.FavoriteTopBarTitle
import com.akmere.travelling_app.presentation.screen.favorite.listing.FavoriteOfferListing
import com.akmere.travelling_app.presentation.screen.home.components.navigateToHome
import com.akmere.travelling_app.presentation.screen.home.components.navigateToOfferDetails
import com.akmere.travelling_app.presentation.state.UiState
import com.akmere.travelling_app.presentation.viewmodel.FavoriteViewModel
import com.akmere.travelling_app.presentation.viewmodel.factory.FavoriteViewModelFactory

@Composable
fun FavoriteScreen(navHostController: NavHostController) {
    val favoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(
            loadFavoriteOffers = AppDependencies.providesLoadFavoriteOffers(),
            appImageLoader = AppDependencies.providesAppImageLoader(LocalContext.current)
        )
    )

    LaunchedEffect(favoriteViewModel) {
        favoriteViewModel.loadFavoriteOffers()
    }

    favoriteViewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        Scaffold(
            Modifier
                .background(MaterialTheme.colors.primary)
                .padding(16.dp),
            topBar = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BackButton(
                        modifier = Modifier
                            .alpha(0.8f)
                            .background(MaterialTheme.colors.primary)
                            .padding(end = 16.dp)
                    ) {
                        navHostController.navigateToHome()
                    }
                    FavoriteTopBarTitle()
                }
            },
            content = {
                when (uiState) {
                    is UiState.Error -> {
                        //TODO()
                    }
                    UiState.Loading -> LoadingContent()
                    is UiState.Success -> FavoriteOfferListing(
                        uiState.data.favoriteOffers,
                        Modifier.fillMaxSize().padding(top = 32.dp)
                    ) {
                        navHostController.navigateToOfferDetails(it.id)
                    }
                }
            }
        )
    }
}
