package com.akmere.travelling_app.presentation.model

import androidx.annotation.DrawableRes
import com.akmere.travelling_app.R

sealed class OfferCategory(@DrawableRes val imageResource: Int, val contentDescription: String) {
    object Hotel : OfferCategory(R.drawable.ic_outline_hotel_24, "Hotel")
    object Package : OfferCategory(R.drawable.ic_baseline_card_travel_24, "Pacote")
}
