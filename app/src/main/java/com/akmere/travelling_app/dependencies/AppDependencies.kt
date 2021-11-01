package com.akmere.travelling_app.dependencies

import android.content.Context
import coil.ImageLoader
import com.akmere.travelling_app.BuildConfig
import com.akmere.travelling_app.common.DebugLogger
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.data.networking.NetworkClientProvider
import com.akmere.travelling_app.data.repository.FavoriteRepository
import com.akmere.travelling_app.data.repository.OfferRepository
import com.akmere.travelling_app.data.repository.SuggestionsRepository
import com.akmere.travelling_app.data.repository.ViewedOfferRepository
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.data.service.SuggestionService
import com.akmere.travelling_app.domain.AddViewedOffer
import com.akmere.travelling_app.domain.IsOfferFavorite
import com.akmere.travelling_app.domain.LoadFavoriteOffers
import com.akmere.travelling_app.domain.LoadFilterSuggestions
import com.akmere.travelling_app.domain.LoadLastViewedOffers
import com.akmere.travelling_app.domain.LoadOfferDetails
import com.akmere.travelling_app.domain.SaveFavoriteOffer
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

object AppDependencies {
    fun providesLoadLastViewedOffers(): LoadLastViewedOffers {
        return LoadLastViewedOffers(viewedOfferRepository, favoriteRepository, offerService)
    }

    fun providesAddViewedOffer(): AddViewedOffer {
        return AddViewedOffer(viewedOfferRepository)
    }

    fun providesLoadFavoriteOffers(): LoadFavoriteOffers {
        return LoadFavoriteOffers(favoriteRepository, offerService)
    }

    fun providesIsOfferFavorite(): IsOfferFavorite {
        return IsOfferFavorite(favoriteRepository)
    }

    fun providesSaveFavoriteOffer(): SaveFavoriteOffer {
        return SaveFavoriteOffer(favoriteRepository)
    }

    fun providesGetOfferDetails(): LoadOfferDetails {
        return LoadOfferDetails(offerService, favoriteRepository)
    }

    fun providesSearchOffers(): SearchOffers = SearchOffers(offerService, favoriteRepository)

    fun providesAppImageLoader(context: Context): TravellingAppImageLoader =
        TravellingAppImageLoader(providesCoilImageLoader(context), context, logger)

    fun providesLoadFilterSuggestions(): LoadFilterSuggestions {
        return LoadFilterSuggestions(suggestionService)
    }

    private val offerService: OfferService by lazy {
        OfferRepository(apolloClient, logger)
    }

    private val suggestionService: SuggestionService by lazy {
        SuggestionsRepository(apolloClient, logger)
    }

    private val apolloClient: ApolloClient by lazy {
        NetworkClientProvider(httpClient).providesClient()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val logger: Logger? by lazy {
        return@lazy if (BuildConfig.DEBUG) {
            DebugLogger()
        } else {
            null
        }
    }

    private val favoriteRepository: FavoriteRepository by lazy {
        FavoriteRepository()
    }

    private val viewedOfferRepository: ViewedOfferRepository by lazy {
        ViewedOfferRepository()
    }

    private fun providesCoilImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context).build()
    }
}
