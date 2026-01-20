package br.com.teya.challenge.domain

import br.com.teya.challenge.common.result.Result
import br.com.teya.challenge.common.result.ResultErrorMessage
import br.com.teya.challenge.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.data.model.AlbumEntry

internal class AlbumRepositoryImpl(
    private val localDataSource: AlbumsLocalDataSource,
) : AlbumRepository {
    override suspend fun fetchAlbums(id: String): Result<AlbumEntry> {
        val response = localDataSource.fetchAlbum(id)
        if (response != null) {
            return Result.Success(response)
        }

        // TODO - handle error
        return Result.Error(
            ResultErrorMessage.StringMessage("not found")
        )

    }
}