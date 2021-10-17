package com.akmere.travelling_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.akmere.travelling_app.dependencies.AppDependencies
import com.akmere.travelling_app.presentation.common.AppTheme
import com.akmere.travelling_app.presentation.home.HomeScreen
import com.akmere.travelling_app.presentation.viewmodel.PopularOffersViewModel
import com.akmere.travelling_app.presentation.viewmodel.factory.ViewModelFactory

class MainActivity : AppCompatActivity() {
    val popularOffersViewModel: PopularOffersViewModel by viewModels {
        ViewModelFactory(
            owner = this,
            searchOffers = AppDependencies.providesSearchOffers(),
            travelAppImageLoader = AppDependencies.providesAppImageLoader(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreen(popularOffersViewModel = popularOffersViewModel)
            }
        }
    }
}
