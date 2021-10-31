package com.akmere.travelling_app.common.error

class AddressNotFoundError(override val message: String? = DEFAULT_MESSAGE) : Exception() {
    companion object {
        const val DEFAULT_MESSAGE = "Nenhum endereço encontrado"
    }
}