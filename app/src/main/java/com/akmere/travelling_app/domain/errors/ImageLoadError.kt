package com.akmere.travelling_app.domain.errors

class ImageLoadError(override val message: String? = DEFAULT_MESSAGE) : Exception() {
    companion object {
        private const val DEFAULT_MESSAGE = "Falha ao carregar imagem"
    }
}
