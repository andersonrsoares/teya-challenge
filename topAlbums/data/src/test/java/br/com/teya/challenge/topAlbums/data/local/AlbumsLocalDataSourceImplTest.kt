package br.com.teya.challenge.topAlbums.data.local

import br.com.teya.challenge.topAlbums.data.local.database.AlbumsDao
import br.com.teya.challenge.topAlbums.data.local.database.entities.AlbumEntity
import br.com.teya.challenge.topAlbums.data.local.database.entities.AlbumImageEntity
import br.com.teya.challenge.topAlbums.domain.models.Album
import br.com.teya.challenge.topAlbums.domain.models.AlbumImage
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AlbumsLocalDataSourceImplTest {

    private val albumsDao: AlbumsDao = mockk()
    private lateinit var dataSource: AlbumsLocalDataSourceImpl

    @Before
    fun setup() {
        dataSource = AlbumsLocalDataSourceImpl(albumsDao)
    }

    @Test
    fun `fetchAlbum returns mapped result`() = runTest {
        // given
        coEvery { albumsDao.fetchAlbum("1") } returns fakeAlbumEntity

        // when
        val result = dataSource.fetchAlbum("1")

        // then
        TestCase.assertEquals(fakeAlbum, result)

        coVerify(exactly = 1) { albumsDao.fetchAlbum("1") }
    }

    @Test
    fun `saveAlbums maps and insert albums`() = runTest {
        // given
        coEvery { albumsDao.insertAll(listOf(fakeAlbumEntity)) } just runs

        // when
        dataSource.saveAlbums(listOf(fakeAlbum))

        // then
        coVerify(exactly = 1) { albumsDao.insertAll(listOf(fakeAlbumEntity)) }
    }


    companion object Companion {

        val fakeAlbumEntity = AlbumEntity(
            id = "12345",
            name = "Fake Album Title 1",
            artist = "Fake Artist 1",
            releaseDate = "January 1, 2024",
            category = "Rock",
            link = "https://example.com/album/12345",
            rights = "© 2024 Fake Record Label",
            image = AlbumImageEntity(
                label = "https://example.com/image_1_large.jpg",
                height = 170
            )
        )

        val fakeAlbum = Album(
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
        )
    }
}