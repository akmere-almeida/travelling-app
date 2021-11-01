package com.akmere.travelling_app.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import com.akmere.travelling_app.domain.LoadLastViewedOffers
import com.akmere.travelling_app.domain.SearchOffers
import com.akmere.travelling_app.domain.TravellingAppImageLoader
import com.akmere.travelling_app.domain.errors.SearchOffersNotFoundError
import com.akmere.travelling_app.domain.model.ImageItem
import com.akmere.travelling_app.domain.model.TravelOffer
import com.akmere.travelling_app.presentation.model.FilterOptions
import com.akmere.travelling_app.presentation.model.OfferCategory
import com.akmere.travelling_app.presentation.model.PopularOffer
import com.akmere.travelling_app.presentation.state.PopularOfferListingState
import com.akmere.travelling_app.presentation.state.UiState
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

    @MockK
    private lateinit var loadLastViewedOffers: LoadLastViewedOffers

    @RelaxedMockK
    private lateinit var mockDrawable: Drawable

    @RelaxedMockK
    private lateinit var bitmap: Bitmap

    private val filterOptions: FilterOptions =
        FilterOptions("city", OfferCategory.Package, "")

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic("androidx.core.graphics.drawable.DrawableKt")
        homeViewModel = HomeViewModel(searchOffers, travellingAppImageLoader, loadLastViewedOffers)

        coEvery { travellingAppImageLoader.loadFromNetwork(any<List<String>>()) } returns mockk(
            relaxed = true
        )
        every { mockDrawable.toBitmap(any(), any(), any()) } returns bitmap
        coEvery { travellingAppImageLoader.loadFromNetwork(any<String>()) } returns mockDrawable
    }

    @Test
    fun `ui states should be loading when begin to load offers`() {
        val observer = mockk<Observer<UiState<PopularOfferListingState>>>()

        val slot = slot<UiState<PopularOfferListingState>>()

        val uiStates = mutableListOf<UiState<PopularOfferListingState>>()

        homeViewModel.popularOffersUiState.observeForever(observer)
        val travelOffers: List<TravelOffer> = listOf(
            mockk(relaxed = true),
            mockk(relaxed = true)
        )

        coEvery { searchOffers.execute(filterOptions) } returns travelOffers

        every {
            observer.onChanged(capture(slot))
        } answers { uiStates.add(slot.captured) }

        homeViewModel.loadPopularOffersData(filterOptions)

        assertEquals(UiState.Loading, uiStates.first())
    }

    @Test
    fun `should successfully load popular offers when search for offers`() {
        val image: ImageItem = mockk(relaxed = true)
        val travelOffers: List<TravelOffer> = listOf(
            TravelOffer("test", "", "", image, 20),
            TravelOffer("test2", "", "", image, 25),
        )

        val expectedState = PopularOfferListingState(
            listOf(
                PopularOffer("offer1", "test", "20", bitmap),
                PopularOffer("offer2", "test", "25", bitmap),
            )
        )

        coEvery { searchOffers.execute(filterOptions) } returns travelOffers

        homeViewModel.loadPopularOffersData(filterOptions)

        assertEquals(UiState.Success(expectedState), homeViewModel.popularOffersUiState.value)
    }

    @Test
    fun `should update ui state with error when failed to search for offers`() {
        val error = SearchOffersNotFoundError()

        coEvery { searchOffers.execute(filterOptions) } throws error

        homeViewModel.loadPopularOffersData(filterOptions)

        assertEquals(UiState.Error(error), homeViewModel.popularOffersUiState.value)
    }

    @Test
    fun `popular offers should have images`() = runBlocking {
        val loadedImages = listOf(mockDrawable, mockDrawable)

        val imageItem = mockk<ImageItem>(relaxed = true)
        val travelOffers: List<TravelOffer> = listOf(
            TravelOffer("test", "", "", imageItem, 20),
            TravelOffer("test2", "", "", imageItem, 25),
        )

        val expectedState = PopularOfferListingState(
            listOf(
                PopularOffer("offer1", "test", "20", bitmap),
                PopularOffer("offer2", "test", "25", bitmap),
            )
        )

        val imageUrls = travelOffers.map { it.image.url }

        coEvery { travellingAppImageLoader.loadFromNetwork(imageUrls) } returns loadedImages
        coEvery { searchOffers.execute(filterOptions) } returns travelOffers

        homeViewModel.loadPopularOffersData(filterOptions)

        coVerify(exactly = 2) {
            travellingAppImageLoader.loadFromNetwork(any<String>())
        }

        assertEquals(UiState.Success(expectedState), homeViewModel.popularOffersUiState.value)
    }
}
