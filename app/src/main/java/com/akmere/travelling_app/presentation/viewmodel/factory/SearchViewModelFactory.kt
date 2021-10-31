package com.akmere.travelling_app.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akmere.travelling_app.domain.*
import com.akmere.travelling_app.presentation.viewmodel.SearchViewModel


class SearchViewModelFactory(
    private val loadFilterSuggestions: LoadFilterSuggestions,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(loadFilterSuggestions) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
