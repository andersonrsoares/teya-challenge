package br.com.teya.challenge.domain

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.data.remote.TopAlbumsRemoteDataSource
import br.com.teya.challenge.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.data.model.TopAlbums
import br.com.teya.challenge.common.result.mapToDataStateResult

internal class TopAlbumsRepositoryImpl(
    private val remoteDataSource: TopAlbumsRemoteDataSource,
    private val localDataSource: AlbumsLocalDataSource,
): TopAlbumsRepository {
    override suspend fun fetchTopAlbums(): DataStateResult<TopAlbums> {
        return remoteDataSource.fetchTopAlbums().mapToDataStateResult(
            onSuccess = {
                localDataSource.saveAlbums(it.feed.entry)
            },
        )
    }
}