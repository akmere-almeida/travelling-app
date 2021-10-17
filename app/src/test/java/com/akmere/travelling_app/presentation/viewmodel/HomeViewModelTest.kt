package com.akmere.travelling_app.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.domain.model.TravelOffer
import com.akmere.travelling_app.presentation.UiState
import com.akmere.travelling_app.presentation.home.model.FilterOptions
import com.akmere.travelling_app.presentation.home.model.PopularOffer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @MockK
    private lateinit var searchOffers: SearchOffers

    @MockK
    private lateinit var travellingAppImageLoader: TravellingAppImageLoader

    @RelaxedMockK
    private lateinit var mockDrawable: Drawable

    @RelaxedMockK
    private lateinit var bitmap: Bitmap

    private val filterOptions: FilterOptions = FilterOptions("city", "state")

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic("androidx.core.graphics.drawable.DrawableKt")
        homeViewModel = HomeViewModel(searchOffers, travellingAppImageLoader)

        coEvery { travellingAppImageLoader.loadFromNetwork(any<List<String>>()) } returns mockk(
            relaxed = true
        )
        every { mockDrawable.toBitmap(any(), any(), any()) } returns bitmap
        coEvery { travellingAppImageLoader.loadFromNetwork(any<String>()) } returns mockDrawable
    }

    @Test
    fun `ui state should be loading when begin to load offers`() {
        val observer = mockk<Observer<UiState<HomeState>>>()

        val slot = slot<UiState<HomeState>>()

        val uiStates = mutableListOf<UiState<HomeState>>()

        homeViewModel.uiState.observeForever(observer)
        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            mockk(relaxed = true),
            mockk(relaxed = true)
        )

        coEvery { searchOffers.execute(filterOptions) } returns travelOffers

        every {
            observer.onChanged(capture(slot))
        } answers { uiStates.add(slot.captured) }

        homeViewModel.loadHomeData(filterOptions)

        assertEquals(UiState.Loading, uiStates.first())
    }

    @Test
    fun `should successfully load popular offers when search for offers`() {
        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            TravelOffer.PackageOffer("test", "", "", "", 20),
            TravelOffer.PackageOffer("test2", "", "", "", 25),
        )

        val expectedState = HomeState(listOf(
            PopularOffer("test", "20", bitmap),
            PopularOffer("test2", "25", bitmap),
        ), "city, state")

        coEvery { searchOffers.execute(filterOptions) } returns travelOffers

        homeViewModel.loadHomeData(filterOptions)

        assertEquals(UiState.Success(expectedState), homeViewModel.uiState.value)
    }

    @Test
    fun `should update ui state with error when failed to search for offers`() {
        val error = SearchOffersNotFoundError()

        coEvery { searchOffers.execute(filterOptions) } throws error

        homeViewModel.loadHomeData(filterOptions)

        assertEquals(UiState.Error(error), homeViewModel.uiState.value)
    }

    @Test
    fun `should load all offer images before updating ui state`() = runBlocking {
        val loadedImages = listOf(mockDrawable, mockDrawable)

        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            TravelOffer.PackageOffer("test", "", "", "", 20),
            TravelOffer.PackageOffer("test2", "", "", "", 25),
        )

        val expectedState = HomeState(listOf(
            PopularOffer("test", "20", bitmap),
            PopularOffer("test2", "25", bitmap),
        ), "city, state")

        val imageUrls = travelOffers.map { it.imageUrl }

        coEvery { travellingAppImageLoader.loadFromNetwork(imageUrls) } returns loadedImages
        coEvery { searchOffers.execute(filterOptions) } returns travelOffers

        homeViewModel.loadHomeData(filterOptions)

        coVerify(exactly = 2) {
            travellingAppImageLoader.loadFromNetwork(any<String>())
        }

        assertEquals(UiState.Success(expectedState), homeViewModel.uiState.value)
    }
}
