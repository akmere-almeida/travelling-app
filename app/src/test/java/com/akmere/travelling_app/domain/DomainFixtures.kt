package com.akmere.travelling_app.domain

import com.akmere.travelling_app.common.OfferExtensions.toTravelOffer
import com.akmere.travelling_app.data.DataFixtures

object DomainFixtures {
    val offersOrderedDescendingByFavoriteCount = listOf(
        DataFixtures.rioPackageOffer.toTravelOffer(200),
        DataFixtures.paratyPackageOfferCopy.toTravelOffer(150),
        DataFixtures.rioPackageOfferCopy.toTravelOffer(149),
        DataFixtures.paratyPackageOffer.toTravelOffer(100)
    )

    val offersOrderedByViewedPosition = DataFixtures.offersData
        .map { it.toTravelOffer(10) }
}
