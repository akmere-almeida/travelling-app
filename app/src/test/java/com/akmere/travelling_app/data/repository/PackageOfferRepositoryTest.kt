package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.data.DataFixtures
import com.akmere.travelling_app.data.JsonReader
import com.akmere.travelling_app.data.model.PackageOfferData
import com.apollographql.apollo.ApolloClient
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PackageOfferRepositoryTest {
    private val MOCK_WEBSERVER_PORT = 9900
    private val mockWebServer = MockWebServer()

    private lateinit var fakeApolloClient: ApolloClient

    @Before
    fun setup() {
        mockWebServer.start(MOCK_WEBSERVER_PORT)
        fakeApolloClient = ApolloClient.builder().serverUrl(mockWebServer.url("/")).build()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should retrieve a collection of package offer data when requested`() = runBlocking {
        val packageOfferRepository: OfferRepository<PackageOfferData> =
            PackageOfferRepository(fakeApolloClient)
        val successResponse = JsonReader.readFile("success_response.json")

        mockWebServer.enqueue(MockResponse().setBody(successResponse))

        val expectedCollection = listOf(
            DataFixtures.rioPackageOffer,
            DataFixtures.paratyPackageOffer
        )

        val result = packageOfferRepository.getOffers(1, listOf())

        assertEquals(expectedCollection, result)
    }
}
