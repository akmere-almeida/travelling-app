package com.akmere.travelling_app.presentation.viewmodel

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmere.travelling_app.domain.LoadLastViewedOffers
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.presentation.model.FilterOptions
import com.akmere.travelling_app.presentation.model.OfferCategory
import com.akmere.travelling_app.presentation.model.PopularOffer
import com.akmere.travelling_app.presentation.model.ViewedOffer
import com.akmere.travelling_app.presentation.state.PopularOfferListingState
import com.akmere.travelling_app.presentation.state.UiState
import com.akmere.travelling_app.presentation.state.ViewedOfferListingState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchOffers: SearchOffers,
    private val travelAppImageLoader: TravellingAppImageLoader,
    private val loadLastViewedOffers: LoadLastViewedOffers
) : ViewModel() {

    private var _selectedCategory: MutableLiveData<OfferCategory> =
        MutableLiveData(OfferCategory.Hotel)
    val selectedCategory: LiveData<OfferCategory>
        get() = _selectedCategory

    private val _popularOffersUiState: MutableLiveData<UiState<PopularOfferListingState>> =
        MutableLiveData()
    val popularOffersUiState: LiveData<UiState<PopularOfferListingState>>
        get() = _popularOffersUiState

    private val _viewedOffersUiState: MutableLiveData<UiState<ViewedOfferListingState>> =
        MutableLiveData()
    val viewedOffersUiState: LiveData<UiState<ViewedOfferListingState>>
        get() = _viewedOffersUiState

    fun loadLastViewedOffersData() {
        viewModelScope.launch {
            runCatching {
                loadLastViewedOffers.execute().associateWith {
                    loadImageResourceAsBitmap(it.image.url)
                }.map {
                    ViewedOffer(
                        id = it.key.id,
                        title = it.key.name,
                        location = it.key.address,
                        image = it.value
                    )
                }
            }.onSuccess {
                _viewedOffersUiState.value = UiState.Success(ViewedOfferListingState(it))
            }.onFailure {
                _viewedOffersUiState.value = UiState.Error(Exception(it.cause?.message))
            }
        }
    }

    fun loadPopularOffersData(filterOptions: FilterOptions) {
        val selectedCategory = selectedCategory.value
        _popularOffersUiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response =
                    searchOffers.execute(filterOptions.copy(offerCategory = selectedCategory))
                val popularOffers =
                    response.associateWith {
                        loadImageResourceAsBitmap(it.image.url)
                    }.map {
                        PopularOffer(
                            it.key.id,
                            it.key.name,
                            it.key.favoriteCount.toString(),
                            it.value
                        )
                    }
                _popularOffersUiState.value = UiState.Success(
                    PopularOfferListingState(
                        popularOffers,
                    )
                )
            } catch (e: SearchOffersNotFoundError) {
                _popularOffersUiState.value = UiState.Error(e)
            }
        }
    }

    private suspend fun loadImageResourceAsBitmap(imageUrl: String): Bitmap {
        return travelAppImageLoader.loadFromNetwork(imageUrl).toBitmap()
    }

    fun setOfferCategory(offerCategory: OfferCategory) {
        _selectedCategory.value = offerCategory
    }
}
