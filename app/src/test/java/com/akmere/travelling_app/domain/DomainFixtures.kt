package com.akmere.travelling_app.domain

import com.akmere.travelling_app.common.OfferExtensions.toTravelOffer
import com.akmere.travelling_app.data.DataFixtures

object DomainFixtures {
    val orderedDescendingByFavoriteCountOffers = listOf(
        DataFixtures.paratyPackageOffer.toTravelOffer(200),
        DataFixtures.rioPackageOffer.toTravelOffer(150),
        DataFixtures.rioPackageOffer.toTravelOffer(149),
        DataFixtures.paratyPackageOffer.toTravelOffer(100),
    )
}
