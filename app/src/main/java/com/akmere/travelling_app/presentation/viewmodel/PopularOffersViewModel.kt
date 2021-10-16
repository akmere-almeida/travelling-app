package com.akmere.travelling_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.model.TravelOffer
import com.akmere.travelling_app.presentation.UiState
import com.akmere.travelling_app.presentation.home.model.PopularOffer

class PopularOffersViewModel(private val searchOffers: SearchOffers) : ViewModel() {

    private val internalUiState: MutableLiveData<UiState<List<PopularOffer>>> = MutableLiveData()
    val uiState: LiveData<UiState<List<PopularOffer>>>
        get() = internalUiState

    suspend fun loadPopularOffers() {
        internalUiState.value = UiState.Loading
        val response = searchOffers.execute()
        val data: List<PopularOffer> = response.map {
            if (it is TravelOffer.PackageOffer) {
                PopularOffer(it.name, "20", it.imageUrl)
            } else {
                throw IndexOutOfBoundsException()
            }
        }
        internalUiState.value = UiState.Success(data)
    }
}
