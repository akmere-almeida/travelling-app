package com.akmere.travelling_app.presentation.viewmodel

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.domain.model.TravelOffer
import com.akmere.travelling_app.presentation.UiState
import com.akmere.travelling_app.presentation.home.model.FilterOptions
import com.akmere.travelling_app.presentation.home.model.PopularOffer
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchOffers: SearchOffers,
    private val travelAppImageLoader: TravellingAppImageLoader
) : ViewModel() {

    private val internalUiState: MutableLiveData<UiState<List<PopularOffer>>> = MutableLiveData()
    val uiState: LiveData<UiState<List<PopularOffer>>>
        get() = internalUiState

    fun loadPopularOffers(filterOptions: FilterOptions) {
        internalUiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = searchOffers.execute(filterOptions)
                val popularOffers =
                    response.filterIsInstance<TravelOffer.PackageOffer>().associateWith {
                        loadImageResourceAsBitmap(it.imageUrl)
                    }.map {
                        PopularOffer(it.key.name, it.key.favoriteCount.toString(), it.value)
                    }
                internalUiState.value = UiState.Success(popularOffers)
            } catch (e: SearchOffersNotFoundError) {
                internalUiState.value = UiState.Error(e)
            }
        }
    }

    private suspend fun loadImageResourceAsBitmap(imageUrl: String): Bitmap {
        return travelAppImageLoader.loadFromNetwork(imageUrl).toBitmap()
    }
}
