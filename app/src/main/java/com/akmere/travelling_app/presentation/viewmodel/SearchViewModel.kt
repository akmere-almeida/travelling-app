package com.akmere.travelling_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmere.travelling_app.domain.LoadFilterSuggestions
import com.akmere.travelling_app.domain.model.FilterSuggestion
import com.akmere.travelling_app.presentation.state.UiState
import com.akmere.travelling_app.presentation.model.Suggestion
import com.akmere.travelling_app.presentation.state.SearchState
import kotlinx.coroutines.launch

class SearchViewModel(private val loadFilterSuggestions: LoadFilterSuggestions) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<SearchState>> = MutableLiveData()
    val uiState: LiveData<UiState<SearchState>>
        get() = _uiState

    fun loadSuggestions(userInput: String) {
        viewModelScope.launch {
            try {
                val filterSuggestions = loadFilterSuggestions.execute(userInput)
                _uiState.value = UiState.Success(
                    SearchState(suggestions = filterSuggestions.toSuggestions())
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }

    private fun List<FilterSuggestion>.toSuggestions(): List<Suggestion> {
        return map {
            Suggestion(it.name, it.filter)
        }
    }
}