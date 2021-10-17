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
import com.akmere.travelling_app.presentation.home.model.PopularOffer
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
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

    @RelaxedMockK
    private lateinit var mockDrawable: Drawable

    @RelaxedMockK
    private lateinit var bitmap: Bitmap

    private lateinit var popularOffersViewModel: PopularOffersViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic("androidx.core.graphics.drawable.DrawableKt")
        popularOffersViewModel = PopularOffersViewModel(searchOffers, travellingAppImageLoader)

        coEvery { travellingAppImageLoader.loadFromNetwork(any<List<String>>()) } returns mockk(
            relaxed = true
        )
        every { mockDrawable.toBitmap(any(), any(), any()) } returns bitmap
        coEvery { travellingAppImageLoader.loadFromNetwork(any<String>()) } returns mockDrawable
    }

    @Test
    fun `ui state should be loading when begin to load offers`() {
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
    fun `should successfully load popular offers when search for offers`() {
        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            TravelOffer.PackageOffer("test", "", "", "", 20),
            TravelOffer.PackageOffer("test2", "", "", "", 25),
        )

        val expectedPopularOffers: List<PopularOffer> = listOf(
            PopularOffer("test", "20", bitmap),
            PopularOffer("test2", "25", bitmap),
        )

        coEvery { searchOffers.execute() } returns travelOffers

        popularOffersViewModel.loadPopularOffers()

        assertEquals(UiState.Success(expectedPopularOffers), popularOffersViewModel.uiState.value)
    }

    @Test
    fun `should update ui state with error when failed to search for offers`() {
        val error = SearchOffersNotFoundError()

        coEvery { searchOffers.execute() } throws error

        popularOffersViewModel.loadPopularOffers()

        assertEquals(UiState.Error(error), popularOffersViewModel.uiState.value)
    }

    @Test
    fun `should load all offer images before updating ui state`() = runBlocking {
        val loadedImages = listOf(mockDrawable, mockDrawable)

        val travelOffers: List<TravelOffer.PackageOffer> = listOf(
            TravelOffer.PackageOffer("test", "", "", "", 20),
            TravelOffer.PackageOffer("test2", "", "", "", 25),
        )

        val expectedPopularOffers: List<PopularOffer> = listOf(
            PopularOffer("test", "20", bitmap),
            PopularOffer("test2", "25", bitmap),
        )

        val imageUrls = travelOffers.map { it.imageUrl }

        coEvery { travellingAppImageLoader.loadFromNetwork(imageUrls) } returns loadedImages
        coEvery { searchOffers.execute() } returns travelOffers

        popularOffersViewModel.loadPopularOffers()

        coVerify(exactly = 2) {
            travellingAppImageLoader.loadFromNetwork(any<String>())
        }

        assertEquals(UiState.Success(expectedPopularOffers), popularOffersViewModel.uiState.value)
    }
}
