package com.akmere.travelling_app.presentation.screen.home.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.akmere.travelling_app.presentation.common.Constants.Navigation.DESTINATION_FAVORITE_SCREEN
import com.akmere.travelling_app.presentation.common.Constants.Navigation.DESTINATION_HOME_SCREEN
import com.akmere.travelling_app.presentation.common.Constants.Navigation.DESTINATION_OFFER_DETAILS_SCREEN
import com.akmere.travelling_app.presentation.common.Constants.Navigation.DESTINATION_SEARCH_SCREEN
import com.akmere.travelling_app.presentation.screen.favorite.FavoriteScreen
import com.akmere.travelling_app.presentation.screen.home.HomeScreen
import com.akmere.travelling_app.presentation.screen.offer_details.OfferDetailScreen
import com.akmere.travelling_app.presentation.screen.search.SearchScreen
import com.akmere.travelling_app.presentation.viewmodel.HomeViewModel

@Composable
fun ScreenNavigator(navController: NavHostController, onHomeStarted: (HomeViewModel) -> Unit) {
    val uri = "https://example.com"

    NavHost(
        navController = navController,
        startDestination = DESTINATION_HOME_SCREEN
    ) {
        composable(
            DESTINATION_HOME_SCREEN
        ) {
            HomeScreen(navController, onHomeStarted)
        }
        composable(
            "$DESTINATION_OFFER_DETAILS_SCREEN{id}",
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })
        )
        {
            OfferDetailScreen(navController, it.arguments?.getString("id") ?: "")
        }
        composable(DESTINATION_SEARCH_SCREEN)
        {
            SearchScreen(navController)
        }
        composable(DESTINATION_FAVORITE_SCREEN)
        {
            FavoriteScreen(navController)
        }
    }
}

fun NavHostController.navigateToOfferDetails(
    offerId: String
) {
    navigate("$DESTINATION_OFFER_DETAILS_SCREEN${offerId}") {
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToHome(
) {
    navigate(DESTINATION_HOME_SCREEN) {
        popUpTo(graph.findStartDestination().id){
            inclusive = true
        }
    }
}
