package com.akmere.travelling_app.data.repository

import android.util.Log
import com.akmere.travelling_app.GetSuggestionsQuery
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.data.model.SuggestionDataItem
import com.akmere.travelling_app.data.model.SuggestionType
import com.akmere.travelling_app.data.service.exceptions.SuggestionParseException
import com.akmere.travelling_app.data.service.exceptions.UnexpectedLoadException
import com.akmere.travelling_app.data.service.SuggestionService
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloParseException

/**
 * Classe concreta que implementa [SuggestionService] usada para comunicação com API de Sugestões
 *
 * @param datasource cliente GraphQL para carregar informações da API
 *
 * @return uma lista de [SuggestionDataItem] contendo informações da sugestão
 *
 * @throws SuggestionParseException
 * @throws UnexpectedLoadException
 */
class SuggestionsRepository(
    private val datasource: ApolloClient,
    private val logger: Logger? = null
) : SuggestionService {

    override suspend fun getSuggestions(
        searchTerm: String
    ): List<SuggestionDataItem> {
        return runCatching {
            datasource.query(
                GetSuggestionsQuery(
                    searchTerm
                )
            ).await().data?.suggestions?.results?.map {
                it.toSuggestionDataItem()
            }.orEmpty()
        }.onFailure {
            logger?.log(TAG, Log.DEBUG, message = it.cause?.message, throwable = it)
            if (it is ApolloParseException)
                throw SuggestionParseException()

            throw UnexpectedLoadException()
        }.onSuccess { response ->
            logger?.log(
                TAG,
                Log.DEBUG,
                message = response.toString(),
                throwable = null
            )
        }.getOrThrow()
    }

    private fun GetSuggestionsQuery.Result.toSuggestionDataItem(): SuggestionDataItem {
        val parsedFilter = filter?.split("|")?.first() ?: ""

        val suggestionType: SuggestionType = when (this.suggestionType) {
            SUGGESTION_TYPE_CITY -> SuggestionType.CITY
            SUGGESTION_TYPE_STATE -> SuggestionType.STATE
            SUGGESTION_TYPE_COUNTRY -> SuggestionType.COUNTRY
            else -> SuggestionType.OTHERS
        }

        return SuggestionDataItem(
            name = this.text ?: "",
            suggestionType = suggestionType,
            filter = parsedFilter
        )
    }

    companion object {
        private const val TAG = "SuggestionsRepo"
        private const val SUGGESTION_TYPE_CITY = "city"
        private const val SUGGESTION_TYPE_STATE = "estado"
        private const val SUGGESTION_TYPE_COUNTRY = "country"
    }
}
