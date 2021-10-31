package com.akmere.travelling_app.presentation.screen.offer_details

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes

val OfferDetailsShapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(size = 0f),
    large = RoundedCornerShape(
        bottomEndPercent = 15,
        bottomStartPercent = 15
    )
)
