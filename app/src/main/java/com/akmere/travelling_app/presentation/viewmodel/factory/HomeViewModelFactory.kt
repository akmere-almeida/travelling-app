package com.akmere.travelling_app.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akmere.travelling_app.domain.*
import com.akmere.travelling_app.presentation.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val searchOffers: SearchOffers,
    private val travelAppImageLoader: TravellingAppImageLoader,
    private val loadLastViewedOffers: LoadLastViewedOffers
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                searchOffers,
                travelAppImageLoader,
                loadLastViewedOffers
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
