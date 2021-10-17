package com.akmere.travelling_app.common

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.akmere.travelling_app.data.repository.exceptions.UnexpectedLoadException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Classe usada para geocodificação reversa
 *
 * @param datasource instância de Geocoder para realizar a geocodificação reversa
 */
class AddressProvider(private val datasource: Geocoder, private val logger: Logger? = null) {

    /**
     * Carrega as informações de endereço do usuário a partir de um [Location]
     * Esta chamada deve sempre ocorrer fora da thread de UI. Pois realiza tarefas de
     * longa duração [Desempenho](https://developer.android.com/kotlin/coroutines-adv?hl=pt-br)
     *
     * @param location instância de [Location] para acessar a localização do usuário
     *
     * @return [UserAddress] contendo informações de endereço do usuário
     *
     * @throws AddressNotFoundError
     * @throws LoadAddressError
     */
    suspend fun loadAddress(location: Location): UserAddress = withContext(Dispatchers.IO) {
        val addresses: MutableList<Address> = mutableListOf()

        return@withContext runCatching {
            addresses.addAll(
                datasource.getFromLocation(
                    location.latitude,
                    location.longitude,  // In this sample, get just a single address.
                    1
                )
            )
            assembleUserAddress(addresses)
        }.onFailure {
            logger?.log(TAG, Log.DEBUG, message = it.cause?.message, throwable = it)
            throw LoadAddressError(it.cause?.message)
        }.onSuccess {
            logger?.log(
                TAG,
                Log.DEBUG,
                message = "Received user address: ${it.city}, ${it.state}",
                throwable = null
            )
        }.getOrThrow()
    }

    private fun assembleUserAddress(addresses: List<Address>): UserAddress {
        return if (addresses.isEmpty()) {
            throw AddressNotFoundError()
        } else {
            with(addresses[0]) {
                UserAddress(subAdminArea, adminArea)
            }
        }
    }

    companion object {
        private const val TAG = "AddressProvider"
    }
}
