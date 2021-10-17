package com.akmere.travelling_app.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import com.akmere.travelling_app.common.Logger
import com.akmere.travelling_app.domain.errors.ImageLoadError
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TravellingAppImageLoaderTest {
    @RelaxedMockK
    private lateinit var imageLoader: ImageLoader

    @RelaxedMockK
    private lateinit var logger: Logger

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var travellingAppImageLoader: TravellingAppImageLoader

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        travellingAppImageLoader = TravellingAppImageLoader(imageLoader, context, logger)
    }

    @Test
    fun `should call third party image loader to load image from network`() = runBlocking {
        val imageUrl = "/someImageUrl"
        val loadImageRequest = slot<ImageRequest>()

        coEvery { imageLoader.execute(capture(loadImageRequest)) } returns mockk(relaxed = true)

        travellingAppImageLoader.loadFromNetwork(imageUrl)

        coVerify {
            imageLoader.execute(loadImageRequest.captured)
        }
    }

    @Test(expected = ImageLoadError::class)
    fun `should fail to load images from network when image loader has errors`(): Unit =
        runBlocking {
            val imageUrls = listOf("/someImageUrl", "/anotherImageUrl")

            coEvery { imageLoader.execute(any()) } throws Exception()

            travellingAppImageLoader.loadFromNetwork(imageUrls)
        }
}
