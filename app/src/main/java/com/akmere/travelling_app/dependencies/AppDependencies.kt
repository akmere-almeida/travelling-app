package com.akmere.travelling_app.dependencies

import android.content.Context
import coil.ImageLoader
import com.akmere.travelling_app.common.DebugLogger
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.data.model.PackageOfferData
import com.akmere.travelling_app.data.networking.NetworkClientProvider
import com.akmere.travelling_app.data.repository.OfferRepository
import com.akmere.travelling_app.data.repository.PackageOfferRepository
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

object AppDependencies {

    fun providesSearchOffers(): SearchOffers = SearchOffers(packageOfferRepository)

    fun providesAppImageLoader(context: Context): TravellingAppImageLoader =
        TravellingAppImageLoader(providesCoilImageLoader(context), context, logger)

    private val packageOfferRepository: OfferRepository<PackageOfferData> by lazy {
        PackageOfferRepository(apolloClient, logger)
    }

    private val apolloClient: ApolloClient by lazy {
        NetworkClientProvider(httpClient).providesClient()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val logger: Logger by lazy {
        DebugLogger()
    }

    private fun providesCoilImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context).build()
    }
}
