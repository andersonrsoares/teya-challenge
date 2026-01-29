package br.com.teya.challenge.topAlbums.data.repository

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.common.result.ResultErrorMessage
import br.com.teya.challenge.topAlbums.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.topAlbums.domain.models.Album
import br.com.teya.challenge.topAlbums.domain.repositories.AlbumRepository
import br.com.teya.challenge.topAlbums.domain.R

internal class AlbumRepositoryImpl(
    private val localDataSource: AlbumsLocalDataSource,
) : AlbumRepository {
    override suspend fun fetchAlbum(id: String): DataStateResult<Album> {
        val response = localDataSource.fetchAlbum(id)
        if (response != null) {
            return DataStateResult.Success(response)
        }

        return DataStateResult.Error(
            ResultErrorMessage.ResourceMessage(R.string.album_not_found)
        )

    }
}