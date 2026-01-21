package br.com.teya.challenge.data.local

import br.com.teya.challenge.data.local.database.AlbumsDao
import br.com.teya.challenge.data.local.mapper.toEntity
import br.com.teya.challenge.data.model.Album

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