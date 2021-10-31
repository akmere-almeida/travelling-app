package com.akmere.travelling_app.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akmere.travelling_app.domain.*
import com.akmere.travelling_app.presentation.viewmodel.OfferDetailViewModel

class OfferDetailViewModelFactory(
    private val travelAppImageLoader: TravellingAppImageLoader,
    private val loadOfferDetails: LoadOfferDetails,
    private val saveFavoriteOffer: SaveFavoriteOffer,
    private val isOfferFavorite: IsOfferFavorite,
    private val addViewedOffer: AddViewedOffer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfferDetailViewModel::class.java)) {
            return OfferDetailViewModel(
                travelAppImageLoader,
                loadOfferDetails,
                saveFavoriteOffer,
                isOfferFavorite,
                addViewedOffer
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

