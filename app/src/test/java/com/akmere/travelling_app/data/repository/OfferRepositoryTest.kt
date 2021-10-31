package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.common.model.OfferType
import com.akmere.travelling_app.data.DataFixtures
import com.akmere.travelling_app.data.JsonReader
import com.akmere.travelling_app.data.service.OfferService
import com.akmere.travelling_app.data.service.exceptions.OfferParseException
import com.apollographql.apollo.ApolloClient
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OfferRepositoryTest {
    private val MOCK_WEBSERVER_PORT = 9900
    private val mockWebServer = MockWebServer()

    private lateinit var fakeApolloClient: ApolloClient
    private lateinit var offerService: OfferService
    private val offerTypes = listOf(OfferType.PACKAGE, OfferType.HOTEL)

    @Before
    fun setup() {
        mockWebServer.start(MOCK_WEBSERVER_PORT)
        fakeApolloClient = ApolloClient.builder().serverUrl(mockWebServer.url("/")).build()
        offerService = OfferRepository(fakeApolloClient)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test(expected = OfferParseException::class)
    fun `should throw OfferParseException when failed to retrieve offer data due to parsing errors`(): Unit =
        runBlocking {
            val ids = listOf("offer1", "offer2")
            val response = JsonReader.readFile("broken_search_response.json")

            mockWebServer.enqueue(MockResponse().setBody(response))

            offerService.getOffers(ids)
        }

    @Test
    fun `should search offers by ids`() = runBlocking {
        val ids = listOf("offer1", "offer2")
        val response = JsonReader.readFile("search_success_response.json")

        val expectedCollection = listOf(
            DataFixtures.rioPackageOffer,
            DataFixtures.paratyPackageOffer
        )
        mockWebServer.enqueue(MockResponse().setBody(response))

        val result = offerService.getOffers(ids = ids, offerTypes = offerTypes)
        assertEquals(expectedCollection, result)
    }

    @Test
    fun `should search offers by suggestion`() = runBlocking {
        val suggestion = "suggestion"
        val response = JsonReader.readFile("search_success_response.json")

        mockWebServer.enqueue(MockResponse().setBody(response))

        val expectedCollection = listOf(
            DataFixtures.rioPackageOffer,
            DataFixtures.paratyPackageOffer
        )

        val result = offerService.getOffers(suggestion = suggestion, offerTypes = offerTypes)
        assertEquals(expectedCollection, result)
    }

    @Test
    fun `should search offers by search term`() = runBlocking {
        val searchTerm = "search term"
        val response = JsonReader.readFile("search_success_response.json")
        val expectedCollection = listOf(
            DataFixtures.rioPackageOffer,
            DataFixtures.paratyPackageOffer
        )

        mockWebServer.enqueue(MockResponse().setBody(response))

        val result = offerService.getOffers(searchTerm = searchTerm, offerTypes = offerTypes)
        assertEquals(expectedCollection, result)
    }

    @Test
    fun `should get package offer details`() = runBlocking {
        val response = JsonReader.readFile("search_package_success_response.json")
        val expectedOfferDetails = DataFixtures.packageOfferDetails

        mockWebServer.enqueue(MockResponse().setBody(response))

        val result = offerService.getOfferDetailsData("offerId", OfferType.PACKAGE)
        assertEquals(expectedOfferDetails, result)

    }

    @Test
    fun `should get hotel offer details`() = runBlocking {
        val response = JsonReader.readFile("search_hotel_success_response.json")
        val expectedOfferDetails = DataFixtures.hotelOfferDetails

        mockWebServer.enqueue(MockResponse().setBody(response))

        val result = offerService.getOfferDetailsData("offerId", OfferType.HOTEL)
        assertEquals(expectedOfferDetails, result)

    }
}
