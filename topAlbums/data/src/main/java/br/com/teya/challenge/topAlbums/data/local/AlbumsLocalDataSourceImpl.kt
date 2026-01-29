package br.com.teya.challenge.topAlbums.data.local

import br.com.teya.challenge.topAlbums.data.local.database.AlbumsDao
import br.com.teya.challenge.topAlbums.data.local.mappers.toEntity
import br.com.teya.challenge.topAlbums.data.mappers.toEntity
import br.com.teya.challenge.topAlbums.domain.models.Album

internal class AlbumsLocalDataSourceImpl(
    private val albumsDao: AlbumsDao,
): AlbumsLocalDataSource {
    override suspend fun saveAlbums(albums: List<Album>) {
        albumsDao.insertAll(
            albums.map { it.toEntity() }
        )
    }

    override suspend fun fetchAlbum(id: String): Album? {
        return albumsDao.fetchAlbum(id)?.toEntity()
    }
}