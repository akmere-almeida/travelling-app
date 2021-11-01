package com.akmere.travelling_app.presentation.state

import com.akmere.travelling_app.presentation.model.Suggestion

data class SearchState(
    val suggestions: List<Suggestion> = emptyList()
)
