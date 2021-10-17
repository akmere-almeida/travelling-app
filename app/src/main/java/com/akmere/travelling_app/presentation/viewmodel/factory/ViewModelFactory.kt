package com.akmere.travelling_app.presentation.viewmodel.factory

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.presentation.viewmodel.PopularOffersViewModel

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
    private val searchOffers: SearchOffers,
    private val travelAppImageLoader: TravellingAppImageLoader
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        state: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ViewModelFactory::class.java)) {
            return PopularOffersViewModel(searchOffers, travelAppImageLoader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
