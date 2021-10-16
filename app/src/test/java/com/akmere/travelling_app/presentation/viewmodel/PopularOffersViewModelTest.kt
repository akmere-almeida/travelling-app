package com.akmere.travelling_app.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import coil.ImageLoader
import coil.request.ImageRequest
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.domain.model.TravelOffer
import com.akmere.travelling_app.presentation.UiState
import com.akmere.travelling_app.presentation.home.model.PopularOffer
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PopularOffersViewModelTest {
    @MockK
    private lateinit var searchOffers: SearchOffers

    @MockK
    private lateinit var travellingAppImageLoader: TravellingAppImageLoader

    private lateinit var popularOffersViewModel: PopularOffersViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        popularOffersViewModel = PopularOffersViewModel(searchOffers, travellingAppImageLoader)
    }

    @Test
    fun `ui state should be loading when begin to load offers`() = runBlocking {
        val observer = mockk<Observer<UiState<List<PopularOffer>>>>()

        val slot = slot<UiState<List<PopularOffer>>>()

        val uiStates = mutableListOf<UiState<List<PopularOffer>>>()

        popularOffersViewModel.uiState.observeForever(observer)
        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            mockk(relaxed = true),
            mockk(relaxed = true)
        )

        coEvery { searchOffers.execute() } returns travelOffers

        every {
            observer.onChanged(capture(slot))
        } answers { uiStates.add(slot.captured) }

        popularOffersViewModel.loadPopularOffers()

        assertEquals(UiState.Loading, uiStates.first())
    }

    @Test
    fun `should successfully load popular offers when searched for offers`() = runBlocking {
        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            mockk(relaxed = true),
            mockk(relaxed = true)
        )

        val offers: List<PopularOffer> = travelOffers.map {
            PopularOffer(it.name, "20", mockk(relaxed = true))
        }

        coEvery { searchOffers.execute() } returns travelOffers

        popularOffersViewModel.loadPopularOffers()

        assertEquals(UiState.Success(offers), popularOffersViewModel.uiState.value)
    }

    @Test
    fun `should fail to load popular offers when search has errors`() = runBlocking {
        val error = SearchOffersNotFoundError()

        coEvery { searchOffers.execute() } throws error

        popularOffersViewModel.loadPopularOffers()

        assertEquals(UiState.Error(error), popularOffersViewModel.uiState.value)
    }

    @Test
    fun `should load all offer images before updating ui state`() = runBlocking {
        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            mockk(relaxed = true),
            mockk(relaxed = true)
        )

        val defaultImage = mockk<Bitmap>()
        val loadedImages = listOf(defaultImage, defaultImage)

        val imageUrls = travelOffers.map { it.imageUrl }

        val offers: List<PopularOffer> = travelOffers.map {
            PopularOffer(it.name, "20", defaultImage)
        }

        coEvery { travellingAppImageLoader.loadFromNetwork(imageUrls) } returns loadedImages
        coEvery { searchOffers.execute() } returns travelOffers

        popularOffersViewModel.loadPopularOffers()

        coVerify {
            travellingAppImageLoader.loadFromNetwork(imageUrls)
        }

        assertEquals(UiState.Success(offers), popularOffersViewModel.uiState.value)
    }
}
