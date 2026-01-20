package br.com.teya.challenge.data.local

import br.com.teya.challenge.data.local.database.AlbumsDao
import br.com.teya.challenge.data.local.mapper.toEntity
import br.com.teya.challenge.data.model.AlbumEntry

internal class AlbumsLocalDataSourceImpl(
    private val albumsDao: AlbumsDao,
): AlbumsLocalDataSource {
    override suspend fun saveAlbums(albums: List<AlbumEntry>) {
        albumsDao.insertAll(
            albums.map { it.toEntity() }
        )
    }

    override suspend fun fetchAlbum(id: String): AlbumEntry? {
        return albumsDao.fetchAlbum(id)?.toEntity()
    }
}