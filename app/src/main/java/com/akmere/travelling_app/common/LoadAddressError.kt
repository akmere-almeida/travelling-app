package com.akmere.travelling_app.common

class LoadAddressError(override val message: String? = DEFAULT_MESSAGE) : Exception() {
    companion object {
        const val DEFAULT_MESSAGE = "Falha ao recuperar o endereço"
    }
}
