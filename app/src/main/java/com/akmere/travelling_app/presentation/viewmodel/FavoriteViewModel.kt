package com.akmere.travelling_app.presentation.viewmodel

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmere.travelling_app.domain.LoadFavoriteOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.presentation.screen.favorite.model.FavoriteOffer
import com.akmere.travelling_app.presentation.state.FavoriteState
import com.akmere.travelling_app.presentation.state.UiState
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val loadFavoriteOffers: LoadFavoriteOffers,
    private val travelAppImageLoader: TravellingAppImageLoader,
) : ViewModel() {

    private val _uiState: MutableLiveData<UiState<FavoriteState>> = MutableLiveData()
    val uiState: LiveData<UiState<FavoriteState>>
        get() = _uiState

    fun loadFavoriteOffers() {
        viewModelScope.launch {
            runCatching {
                loadFavoriteOffers.execute().associateWith {
                    loadImageResourceAsBitmap(it.image.url)
                }.map {
                    FavoriteOffer(
                        it.key.id,
                        it.key.name,
                        it.value
                    )
                }
            }.onSuccess {
                _uiState.value = UiState.Success(FavoriteState(it))

            }.onFailure {
                _uiState.value = UiState.Error(Exception(it.cause))
            }
        }
    }

    private suspend fun loadImageResourceAsBitmap(imageUrl: String): Bitmap {
        return travelAppImageLoader.loadFromNetwork(imageUrl).toBitmap()
    }
}
