package com.akmere.travelling_app.domain.errors

class SuggestionFiltersNotFoundError(override val message: String = DEFAULT_MESSAGE) : Exception() {
    companion object {
        private const val DEFAULT_MESSAGE = "Nenhuma sugestão encontrada :("
    }
}
