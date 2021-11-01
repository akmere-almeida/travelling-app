package com.akmere.travelling_app.domain.errors

class ImageNotFoundError(override val message: String? = DEFAULT_MESSAGE) : Exception() {
    companion object {
        private const val DEFAULT_MESSAGE = "Imagem não encontrada"
    }
}
