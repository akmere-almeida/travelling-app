package com.akmere.travelling_app.data.networking

import com.akmere.travelling_app.data.DataConstants
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class GraphqlNetworkClient(private val okHttpClient: OkHttpClient) {

    fun providesClient(): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(DataConstants.serverUrl)
            .okHttpClient(okHttpClient)
            .build()
    }
}