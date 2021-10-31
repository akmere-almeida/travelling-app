package com.akmere.travelling_app.presentation.screen.offer_details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.akmere.travelling_app.dependencies.AppDependencies
import com.akmere.travelling_app.presentation.state.UiState
import com.akmere.travelling_app.presentation.common.Constants.Navigation.DESTINATION_HOME_SCREEN
import com.akmere.travelling_app.presentation.common.components.LoadingContent
import com.akmere.travelling_app.presentation.screen.home.components.navigateToHome
import com.akmere.travelling_app.presentation.screen.offer_details.components.ActionButtons
import com.akmere.travelling_app.presentation.screen.offer_details.components.DetailsInfoContent
import com.akmere.travelling_app.presentation.screen.offer_details.components.OfferDetailsImageBox
import com.akmere.travelling_app.presentation.screen.offer_details.listing.ImageGalleryListing
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferDetails
import com.akmere.travelling_app.presentation.viewmodel.OfferDetailViewModel
import com.akmere.travelling_app.presentation.viewmodel.factory.OfferDetailViewModelFactory

@Composable
fun OfferDetailScreen(navController: NavHostController, offerId: String) {
    val offerDetailViewModel: OfferDetailViewModel = viewModel(
        factory = OfferDetailViewModelFactory(
            travelAppImageLoader = AppDependencies.providesAppImageLoader(LocalContext.current),
            loadOfferDetails = AppDependencies.providesGetOfferDetails(),
            loadOfferFavoriteCount = AppDependencies.providesLoadFavoriteCount(),
            saveFavoriteOffer = AppDependencies.providesSaveFavoriteOffer(),
            isOfferFavorite = AppDependencies.providesIsOfferFavorite(),
            addViewedOffer = AppDependencies.providesAddViewedOffer(),
        )
    )

    LaunchedEffect(offerDetailViewModel) {
        offerDetailViewModel.loadOfferDetails(offerId)
        offerDetailViewModel.addViewedOffer(offerId)
    }

    val context = LocalContext.current

    offerDetailViewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Error -> {
                //TODO()
            }
            UiState.Loading -> {
                LoadingContent()
            }
            is UiState.Success -> Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
            ) {
                val offerDetails = uiState.data.offerDetails

                val isFavorite = remember {
                    mutableStateOf(offerDetails.isFavorite)
                }

                OfferDetailsImageBox(
                    offerDetails.mainImage, onImageSelected = {
                        //TODO
                    }, onBackPressed = {
                        navController.navigateToHome()
                    }, onShareClicked = {
                        val shareIntent = createShareIntent(offerDetails)
                        context.startActivity(shareIntent)
                    }
                )
                Column(
                    Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .weight(3f)
                ) {
                    DetailsInfoContent(offerDetails, Modifier.weight(3f), onLocationClicked = {
                        val mapIntent = createMapIntent(it)

                        mapIntent.resolveActivity(context.packageManager)?.let {
                            context.startActivity(mapIntent)
                        }
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                    ImageGalleryListing(offerDetails.gallery.images, Modifier.weight(1f)) {
                        //TODO
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ActionButtons(Modifier.weight(1f), isFavorite.value) {
                        offerDetailViewModel.updateFavoriteOffer(offerDetails)
                        isFavorite.value = !isFavorite.value
                    }
                }
            }
        }
    }
}

private fun createMapIntent(mapUri: Uri): Intent {
    val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
    return mapIntent.setPackage("com.google.android.apps.maps")
}

private fun createShareIntent(offerDetails: OfferDetails): Intent? {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "${offerDetails.title}\nem ${offerDetails.location}\nhttps://example.com/${offerDetails.id}"
        )
    }

    sendIntent.type = "text/plain"
    return Intent.createChooser(sendIntent, "https://example.com/${offerDetails.id}")
}

