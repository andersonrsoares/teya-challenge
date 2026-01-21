package br.com.teya.challenge.remote

import br.com.teya.challenge.common.network.RemoteDataSourceException
import br.com.teya.challenge.data.models.Album
import br.com.teya.challenge.data.models.AlbumImage
import br.com.teya.challenge.data.models.TopAlbumsFeed
import br.com.teya.challenge.data.remote.TopAlbumsRemoteDataSourceImpl
import br.com.teya.challenge.data.remote.TopAlbumsService
import br.com.teya.challenge.data.remote.model.AlbumCategory
import br.com.teya.challenge.data.remote.model.AlbumCategoryAttributes
import br.com.teya.challenge.data.remote.model.AlbumEntryResponse
import br.com.teya.challenge.data.remote.model.AlbumIdAttributesResponse
import br.com.teya.challenge.data.remote.model.AlbumIdResponse
import br.com.teya.challenge.data.remote.model.AlbumImageResponse
import br.com.teya.challenge.data.remote.model.AlbumLabelResponse
import br.com.teya.challenge.data.remote.model.AlbumLink
import br.com.teya.challenge.data.remote.model.AlbumLinkAttributes
import br.com.teya.challenge.data.remote.model.AlbumReleaseAttributes
import br.com.teya.challenge.data.remote.model.AlbumReleaseDate
import br.com.teya.challenge.data.remote.model.FeedResponse
import br.com.teya.challenge.data.remote.model.ImageAttributesResponse
import br.com.teya.challenge.data.remote.model.TopAlbumsResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TopAlbumsRemoteDataSourceImplTest {

    private val service: TopAlbumsService = mockk()
    private lateinit var dataSource: TopAlbumsRemoteDataSourceImpl

    @Before
    fun setup() {
        dataSource = TopAlbumsRemoteDataSourceImpl(service)
    }

    @Test
    fun `fetchTopAlbums returns mapped result when service succeeds`() = runTest {
        // given
        coEvery { service.topAlbums() } returns Result.success(fakeTopAlbumsResponse)

        // when
        val result = dataSource.fetchTopAlbums()

        // then
        assert(result.isSuccess)
        TestCase.assertEquals(fakeTopAlbumsFeed, result.getOrNull())

        coVerify(exactly = 1) { service.topAlbums() }
    }

    @Test
    fun `fetchTopAlbums returns mapped result when service fails`() = runTest {
        // given
        val error = RemoteDataSourceException()
        coEvery { service.topAlbums() } returns Result.failure(error)

        // when
        val result = dataSource.fetchTopAlbums()

        // then
        assert(result.isFailure)
        TestCase.assertEquals(error, result.exceptionOrNull())


        coVerify(exactly = 1) { service.topAlbums() }
    }


    companion object {

        internal val fakeTopAlbumsResponse = TopAlbumsResponse(
            feed = FeedResponse(
                entry = listOf(
                    AlbumEntryResponse(
                        id = AlbumIdResponse(
                            attributes = AlbumIdAttributesResponse(id = "12345")
                        ),
                        name = AlbumLabelResponse(label = "Fake Album Title 1"),
                        images = listOf(
                            AlbumImageResponse(
                                label = "https://example.com/image_1_small.jpg",
                                attributes = ImageAttributesResponse(height = "60")
                            ),
                            AlbumImageResponse(
                                label = "https://example.com/image_1_medium.jpg",
                                attributes = ImageAttributesResponse(height = "100")
                            ),
                            AlbumImageResponse(
                                label = "https://example.com/image_1_large.jpg",
                                attributes = ImageAttributesResponse(height = "170")
                            )
                        ),
                        artist = AlbumLabelResponse(label = "Fake Artist 1"),
                        releaseDate = AlbumReleaseDate(
                            attributes = AlbumReleaseAttributes(label = "January 1, 2024")
                        ),
                        category = AlbumCategory(
                            attributes = AlbumCategoryAttributes(
                                label = "Rock",
                                scheme = "https://example.com/genres/rock"
                            )
                        ),
                        rights = AlbumLabelResponse(label = "© 2024 Fake Record Label"),
                        link = AlbumLink(
                            attributes = AlbumLinkAttributes(href = "https://example.com/album/12345")
                        )
                    ),
                    AlbumEntryResponse(
                        id = AlbumIdResponse(
                            attributes = AlbumIdAttributesResponse(id = "67890")
                        ),
                        name = AlbumLabelResponse(label = "Fake Album Title 2"),
                        images = listOf(
                            AlbumImageResponse(
                                label = "https://example.com/image_2_small.jpg",
                                attributes = ImageAttributesResponse(height = "60")
                            ),
                            AlbumImageResponse(
                                label = "https://example.com/image_2_medium.jpg",
                                attributes = ImageAttributesResponse(height = "100")
                            ),
                            AlbumImageResponse(
                                label = "https://example.com/image_2_large.jpg",
                                attributes = ImageAttributesResponse(height = "170")
                            )
                        ),
                        artist = AlbumLabelResponse(label = "Fake Artist 2"),
                        releaseDate = AlbumReleaseDate(
                            attributes = AlbumReleaseAttributes(label = "February 2, 2024")
                        ),
                        category = AlbumCategory(
                            attributes = AlbumCategoryAttributes(
                                label = "Pop",
                                scheme = "https://example.com/genres/pop"
                            )
                        ),
                        rights = AlbumLabelResponse(label = "© 2024 Another Fake Label"),
                        link = AlbumLink(
                            attributes = AlbumLinkAttributes(href = "https://example.com/album/67890")
                        )
                    )
                )
            )
        )

        val fakeTopAlbumsFeed = TopAlbumsFeed(
            albums = listOf(
                Album(
                    id = "12345",
                    name = "Fake Album Title 1",
                    artist = "Fake Artist 1",
                    releaseDate = "January 1, 2024",
                    category = "Rock",
                    link = "https://example.com/album/12345",
                    rights = "© 2024 Fake Record Label",
                    image = AlbumImage(
                        label = "https://example.com/image_1_large.jpg",
                        height = 170
                    )
                ),
                Album(
                    id = "67890",
                    name = "Fake Album Title 2",
                    artist = "Fake Artist 2",
                    releaseDate = "February 2, 2024",
                    category = "Pop",
                    link = "https://example.com/album/67890",
                    rights = "© 2024 Another Fake Label",
                    image = AlbumImage(
                        label = "https://example.com/image_2_large.jpg",
                        height = 170
                    )
                )
            )
        )
    }
}