package br.com.teya.challenge.topAlbums.data.repository

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.topAlbums.domain.models.TopAlbumsFeed
import br.com.teya.challenge.common.result.mapToDataStateResult
import br.com.teya.challenge.topAlbums.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.topAlbums.data.remote.TopAlbumsRemoteDataSource
import br.com.teya.challenge.topAlbums.domain.repositories.TopAlbumsRepository

internal class TopAlbumsRepositoryImpl(
    private val remoteDataSource: TopAlbumsRemoteDataSource,
    private val localDataSource: AlbumsLocalDataSource,
): TopAlbumsRepository {
    override suspend fun fetchTopAlbums(): DataStateResult<TopAlbumsFeed> {
        return remoteDataSource.fetchTopAlbums().mapToDataStateResult(
            onSuccess = {
                localDataSource.saveAlbums(it.albums)
            },
        )
    }
}