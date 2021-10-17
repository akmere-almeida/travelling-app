package com.akmere.travelling_app.common

class AddressNotFoundError(override val message: String? = DEFAULT_MESSAGE) : Exception() {
    companion object {
        const val DEFAULT_MESSAGE = "Nenhum endereço encontrado"
    }
}
