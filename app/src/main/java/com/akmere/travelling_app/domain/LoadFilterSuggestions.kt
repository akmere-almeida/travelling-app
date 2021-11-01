package com.akmere.travelling_app.domain

import com.akmere.travelling_app.data.model.SuggestionDataItem
import com.akmere.travelling_app.data.service.SuggestionService
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.domain.errors.SuggestionFiltersNotFoundError
import com.akmere.travelling_app.domain.model.FilterSuggestion

/**
 * Caso de uso para buscar por sugestões de filtros
 *
 * @param suggestionService repositório responsável por carregar informações de sugestões
 *
 *
 */
class LoadFilterSuggestions(private val suggestionService: SuggestionService) {

    /**
     *
     * Executa a busca por sugestões
     *
     * @return uma lista de [FilterSuggestion] contendo informações de sugestões de filtros
     *
     * @throws SearchOffersNotFoundError
     * @throws UnknownError
     */
    suspend fun execute(filterInput: String): List<FilterSuggestion> {
        return runCatching {
            suggestionService.getSuggestions(filterInput).apply {
                if (isEmpty())
                    throw SuggestionFiltersNotFoundError()
            }.map { suggestion ->
                suggestion.toFilterSuggestion()
            }
        }.onFailure {
            if (it !is SuggestionFiltersNotFoundError)
                throw UnknownError()
        }.getOrThrow()
    }

    private fun SuggestionDataItem.toFilterSuggestion(): FilterSuggestion {
        return FilterSuggestion(
            name,
            filter
        )
    }
}
