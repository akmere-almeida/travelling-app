package com.akmere.travelling_app.domain

import android.graphics.drawable.Drawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.NullRequestDataException
import com.akmere.travelling_app.domain.errors.ImageLoadError
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TravellingAppImageLoaderTest {
    @RelaxedMockK
    private lateinit var imageLoader: ImageLoader

    private lateinit var travellingAppImageLoader: TravellingAppImageLoader

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        travellingAppImageLoader = TravellingAppImageLoader(imageLoader)
    }

    @Test
    fun `should load image from network`() = runBlocking {
        val imageUrl = "/someImageUrl"
        val expectedImage = mockk<Drawable>()

        val loadImageRequest = mockk<ImageRequest>(relaxed = true){
            every { data } returns imageUrl
        }
        val imageResult = mockk<ImageResult>(relaxed = true){
            every { drawable } returns expectedImage
        }

        coEvery { imageLoader.execute(loadImageRequest) } returns imageResult

        val resultImage = travellingAppImageLoader.loadFromNetwork(imageUrl)

        assertEquals(expectedImage, resultImage)
    }

    @Test(expected = ImageLoadError::class)
    fun `should fail to load images from network when image loader has errors`(): Unit = runBlocking {
        val imageUrls = listOf("/someImageUrl", "/anotherImageUrl")

        coEvery { imageLoader.execute(any()) } throws Exception()

        travellingAppImageLoader.loadFromNetwork(imageUrls)
    }
}
