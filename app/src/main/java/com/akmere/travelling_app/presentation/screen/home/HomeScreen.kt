package com.akmere.travelling_app.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.akmere.travelling_app.dependencies.AppDependencies
import com.akmere.travelling_app.presentation.common.Constants.Navigation.DESTINATION_FAVORITE_SCREEN
import com.akmere.travelling_app.presentation.common.Constants.Navigation.DESTINATION_SEARCH_SCREEN
import com.akmere.travelling_app.presentation.common.Constants.Navigation.SEARCH_SUGGESTION_FILTER_KEY
import com.akmere.travelling_app.presentation.common.Constants.Navigation.SEARCH_TERM_KEY
import com.akmere.travelling_app.presentation.screen.home.listing.OfferCategories.categories
import com.akmere.travelling_app.presentation.common.components.LoadingContent
import com.akmere.travelling_app.presentation.model.FilterOptions
import com.akmere.travelling_app.presentation.model.OfferCategory
import com.akmere.travelling_app.presentation.screen.home.components.HomeBottomAppBar
import com.akmere.travelling_app.presentation.screen.home.components.HomeTopAppBar
import com.akmere.travelling_app.presentation.screen.home.components.SearchBar
import com.akmere.travelling_app.presentation.screen.home.components.navigateToOfferDetails
import com.akmere.travelling_app.presentation.screen.home.listing.OfferCategoryListing
import com.akmere.travelling_app.presentation.screen.home.listing.PopularOfferListing
import com.akmere.travelling_app.presentation.screen.home.listing.ViewedOfferListing
import com.akmere.travelling_app.presentation.state.UiState
import com.akmere.travelling_app.presentation.viewmodel.HomeViewModel
import com.akmere.travelling_app.presentation.viewmodel.factory.HomeViewModelFactory

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            searchOffers = AppDependencies.providesSearchOffers(),
            travelAppImageLoader = AppDependencies.providesAppImageLoader(LocalContext.current),
            loadLastViewedOffers = AppDependencies.providesLoadLastViewedOffers()
        )
    )

    val searchTerm = navHostController.currentBackStackEntry
        ?.savedStateHandle?.getLiveData<String>(SEARCH_TERM_KEY)

    val searchSuggestionFilter = navHostController.currentBackStackEntry
        ?.savedStateHandle?.getLiveData<String>(SEARCH_SUGGESTION_FILTER_KEY)

    val selectedCategory: MutableState<OfferCategory> = remember {
        mutableStateOf(OfferCategory.Hotel)
    }

    LoadHomeData(homeViewModel, searchTerm, searchSuggestionFilter)

    homeViewModel.selectedCategory.observeAsState(initial = OfferCategory.Hotel).value.let {
        selectedCategory.value = it
    }

    Scaffold(
        Modifier.background(MaterialTheme.colors.primary),
        topBar = {
            HomeTopAppBar()
        },
        content = {
            val searchText = remember {
                mutableStateOf("Vai pra onde?")
            }

            val suggestionFilter = remember {
                mutableStateOf("")
            }

            searchTerm?.observeAsState()?.value?.let {
                searchText.value = it
            }

            searchSuggestionFilter?.observeAsState()?.value?.let {
                suggestionFilter.value = it
            }

            val filterOptions = remember {
                FilterOptions(
                    searchText.value,
                    homeViewModel.selectedCategory.value,
                    suggestionFilter.value
                )
            }

            Column(
                Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize(),
            ) {
                OfferCategoryListing(
                    categories,
                    selectedCategory.value
                ) {
                    homeViewModel.setOfferCategory(it)
                    homeViewModel.loadPopularOffersData(
                        filterOptions
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                SearchBar(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    text = searchText.value,
                    onTextClicked = {
                        navHostController.navigate(DESTINATION_SEARCH_SCREEN)
                    }, onSearchClicked = {
                        if (searchText.value != "" && searchText.value != "Vai pra onde?")
                            homeViewModel.loadPopularOffersData(
                                filterOptions
                            )
                    })
                Spacer(modifier = Modifier.height(8.dp))
                PopularOfferContent(homeViewModel, navHostController)
                Spacer(modifier = Modifier.height(8.dp))
                ViewedOfferContent(homeViewModel, navHostController)
            }
        },
        bottomBar = {
            HomeBottomAppBar {
                navHostController.navigate(DESTINATION_FAVORITE_SCREEN)
            }
        }
    )
}

@Composable
private fun ViewedOfferContent(
    homeViewModel: HomeViewModel,
    navHostController: NavHostController
) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Vizualizadas",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondary
        )
        homeViewModel.viewedOffersUiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Error -> {
                    //TODO()
                }
                UiState.Loading -> LoadingContent()
                is UiState.Success ->
                    ViewedOfferListing(uiState.data.lastViewedOffers) {
                        navHostController.navigateToOfferDetails(it.id)
                    }
            }
        }
    }
}

@Composable
private fun PopularOfferContent(
    homeViewModel: HomeViewModel,
    navHostController: NavHostController
) {
    Column {
        Text(
            text = "Destaques",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondary
        )
        homeViewModel.popularOffersUiState.observeAsState(initial = UiState.Loading).value.let { uiState ->

            when (uiState) {
                is UiState.Error -> {
                    //TODO()
                }
                UiState.Loading -> LoadingContent()
                is UiState.Success -> PopularOfferListing(uiState.data.popularOffers) {
                    navHostController.navigateToOfferDetails(it.id)
                }
            }
        }
    }
}

@Composable
private fun LoadHomeData(
    homeViewModel: HomeViewModel,
    searchTerm: MutableLiveData<String>?,
    searchSuggestionFilter: MutableLiveData<String>?
) {
    LaunchedEffect(homeViewModel) {
        homeViewModel.loadPopularOffersData(
            FilterOptions(
                searchTerm?.value,
                homeViewModel.selectedCategory.value,
                searchSuggestionFilter?.value
            )
        )
        homeViewModel.loadLastViewedOffersData()
    }
}
