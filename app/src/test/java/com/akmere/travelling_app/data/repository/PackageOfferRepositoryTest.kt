package com.akmere.travelling_app.data.repository

import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.data.DataFixtures
import com.akmere.travelling_app.data.JsonReader
import com.akmere.travelling_app.data.model.PackageOfferData
import com.akmere.travelling_app.data.repository.exceptions.OfferParseException
import com.akmere.travelling_app.data.repository.exceptions.UnexpectedLoadException
import com.apollographql.apollo.ApolloClient
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
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
    private lateinit var packageOfferRepository: OfferRepository<PackageOfferData>

    @Before
    fun setup() {
        mockWebServer.start(MOCK_WEBSERVER_PORT)
        fakeApolloClient = ApolloClient.builder().serverUrl(mockWebServer.url("/")).build()
        packageOfferRepository = PackageOfferRepository(fakeApolloClient)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        fakeApolloClient
    }

    @Test
    fun `should retrieve a collection of package offer data when requested`() = runBlocking {
        val response = JsonReader.readFile("success_response.json")

        mockWebServer.enqueue(MockResponse().setBody(response))

        val expectedCollection = listOf(
            DataFixtures.rioPackageOffer,
            DataFixtures.paratyPackageOffer
        )

        val result = packageOfferRepository.getOffers(1, listOf())

        assertEquals(expectedCollection, result)
    }

    @Test
    fun `image description should be replaced with package offer name when image description is null`() =
        runBlocking {
            val response =
                JsonReader.readFile("success_response_empty_image_description.json")

            mockWebServer.enqueue(MockResponse().setBody(response))

            val result = packageOfferRepository.getOffers(1, listOf())

            val resultImageDescription = result.first().galleryData.images.first().description
            val resultName = result.first().name

            assertEquals(resultName, resultImageDescription)
        }

    @Test(expected = OfferParseException::class)
    fun `should throw OfferParseException when failed to retrieve data due to parse error`() =
        runBlocking {
            val response = JsonReader.readFile("broken_search_package_offer_response.json")

            mockWebServer.enqueue(MockResponse().setBody(response))

            val expectedCollection = listOf(
                DataFixtures.rioPackageOffer,
                DataFixtures.paratyPackageOffer
            )

            val result = packageOfferRepository.getOffers(1, listOf())

            assertEquals(expectedCollection, result)
        }
}
