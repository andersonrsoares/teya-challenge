package br.com.teya.challenge.domain

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.common.result.ResultErrorMessage
import br.com.teya.challenge.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.data.model.Album

internal class AlbumRepositoryImpl(
    private val localDataSource: AlbumsLocalDataSource,
) : AlbumRepository {
    override suspend fun fetchAlbum(id: String): DataStateResult<Album> {
        val response = localDataSource.fetchAlbum(id)
        if (response != null) {
            return DataStateResult.Success(response)
        }

        // TODO - handle error
        return DataStateResult.Error(
            ResultErrorMessage.StringMessage("not found")
        )

    }
}