package com.akmere.travelling_app.domain.errors

class SearchOffersNotFoundError(override val message: String = DEFAULT_MESSAGE): Exception(){
    companion object {
        const val DEFAULT_MESSAGE = "Nenhuma oferta de viagem encontrada para esta busca :("
    }
}