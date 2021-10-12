package com.akmere.travelling_app.data.networking

import com.akmere.travelling_app.data.DataConstants
import io.mockk.mockk
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test

class GraphqlNetworkClientTest {

    @Test
    fun `graphql server url should be equals to defined at data constants`() {
        val httpClient: OkHttpClient = mockk()

        val graphqlNetworkClient = GraphqlNetworkClient(httpClient)

        val serverUrl = graphqlNetworkClient.providesClient().serverUrl.toString()

        assertEquals(DataConstants.serverUrl, serverUrl)
    }
}