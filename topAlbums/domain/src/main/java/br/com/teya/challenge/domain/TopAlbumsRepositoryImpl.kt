package br.com.teya.challenge.domain

import br.com.teya.challenge.data.remote.TopAlbumsRemoteDataSource
import br.com.teya.challenge.common.result.Result
import br.com.teya.challenge.common.result.ResultErrorMessage
import br.com.teya.challenge.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.data.model.TopAlbums
import br.com.teya.challenge.network.result.mapToResult
import br.com.teya.challenge.network.result.toResultErrorMessage

internal class TopAlbumsRepositoryImpl(
    private val remoteDataSource: TopAlbumsRemoteDataSource,
    private val localDataSource: AlbumsLocalDataSource,
): TopAlbumsRepository {
    override suspend fun fetchTopAlbums(): Result<TopAlbums> {
        return remoteDataSource.fetchTopAlbums().mapToResult(
            onSuccess = {
                localDataSource.saveAlbums(it.feed.entry)
            },
            transform = {error ->
                error.toResultErrorMessage(
                    onServerError = {
                        ResultErrorMessage.StringMessage(it.exception.message.orEmpty())
                    },
                    onNetworkError = {
                        ResultErrorMessage.StringMessage(it.exception.message.orEmpty())
                    }
                )
            }
        )
    }
}