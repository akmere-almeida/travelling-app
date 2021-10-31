package com.akmere.travelling_app.presentation.model

data class FilterOptions(
    val searchTerm: String?,
    val offerCategory: OfferCategory?,
    val suggestionFilter: String?
) {
    companion object {
        val empty = FilterOptions(null, null, null)
    }
}
