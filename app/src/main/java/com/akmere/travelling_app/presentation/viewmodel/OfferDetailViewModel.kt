package com.akmere.travelling_app.presentation.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmere.travelling_app.domain.AddViewedOffer
import com.akmere.travelling_app.domain.IsOfferFavorite
import com.akmere.travelling_app.domain.LoadOfferDetails
import com.akmere.travelling_app.domain.SaveFavoriteOffer
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferDetails
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferGallery
import com.akmere.travelling_app.presentation.screen.offer_details.model.OfferImage
import com.akmere.travelling_app.presentation.state.OfferDetailsState
import com.akmere.travelling_app.presentation.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OfferDetailViewModel(
    private val travelAppImageLoader: TravellingAppImageLoader,
    private val loadOfferDetails: LoadOfferDetails,
    private val saveFavoriteOffer: SaveFavoriteOffer,
    private val isOfferFavorite: IsOfferFavorite,
    private val addViewedOffer: AddViewedOffer
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<OfferDetailsState>> = MutableLiveData()
    val uiState: LiveData<UiState<OfferDetailsState>>
        get() = _uiState

    fun loadOfferDetails(id: String) {
        viewModelScope.launch {
            val offerDetailsResult = loadOfferDetails.execute(id)

            val mainImage = withContext(Dispatchers.Default) {
                OfferImage(
                    offerDetailsResult.mainImage.url,
                    loadImageResourceAsBitmap(offerDetailsResult.mainImage.url),
                    offerDetailsResult.mainImage.description
                )
            }

            val gallery = withContext(Dispatchers.Default) {
                OfferGallery(
                    offerDetailsResult.gallery.map {
                        val image = loadImageResourceAsBitmap(it.url)
                        OfferImage(it.url, image, it.description)
                    }
                )
            }

            val isFavorite = isOfferFavorite.execute(offerDetailsResult.id)

            val offerDetails = OfferDetails(
                id = offerDetailsResult.id,
                mainImage = mainImage,
                title = offerDetailsResult.name,
                location = offerDetailsResult.address,
                locationUri = Uri.parse("geo:${offerDetailsResult.lat},${offerDetailsResult.lon}"),
                about = offerDetailsResult.description,
                gallery = gallery,
                favoriteCount = "${offerDetailsResult.favoriteCount}",
                isFavorite = isFavorite
            )

            _uiState.value = UiState.Success(OfferDetailsState(offerDetails))
        }
    }

    fun updateFavoriteOffer(offerDetails: OfferDetails) {
        saveFavoriteOffer.execute(offerDetails.id, !offerDetails.isFavorite)
    }

    fun addViewedOffer(offerId: String) {
        addViewedOffer.execute(offerId, true)
    }

    private suspend fun loadImageResourceAsBitmap(imageUrl: String): Bitmap {
        return travelAppImageLoader.loadFromNetwork(imageUrl).toBitmap()
    }
}
