package com.akmere.travelling_app.data.networking

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class GraphqlNetworkClient(private val okHttpClient: OkHttpClient) {

    fun providesClient(): ApolloClient {
        TODO()
    }
}