package com.akmere.travelling_app.data.service

import com.akmere.travelling_app.data.model.SuggestionDataItem
import com.akmere.travelling_app.data.service.exceptions.SuggestionParseException
import com.akmere.travelling_app.data.service.exceptions.UnexpectedLoadException

/**
 * Interface usada para comunicação com API de Sugestões
 */
interface SuggestionService {
    /**
     *
     * Carrega as informações de Ofertas
     *
     * @param searchTerm termo para realizar a busca (ex: Rio de Janeiro, Paraty)
     *
     * @return lista de [SuggestionDataItem]
     *
     * @throws SuggestionParseException
     * @throws UnexpectedLoadException
     */
    @Throws(SuggestionParseException::class, UnexpectedLoadException::class)
    suspend fun getSuggestions(searchTerm: String): List<SuggestionDataItem>
}
