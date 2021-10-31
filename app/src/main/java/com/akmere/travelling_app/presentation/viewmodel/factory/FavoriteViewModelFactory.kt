package com.akmere.travelling_app.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akmere.travelling_app.domain.LoadFavoriteOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.presentation.viewmodel.FavoriteViewModel

class FavoriteViewModelFactory(
    private val loadFavoriteOffers: LoadFavoriteOffers,
    private val appImageLoader: TravellingAppImageLoader
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(loadFavoriteOffers, appImageLoader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
