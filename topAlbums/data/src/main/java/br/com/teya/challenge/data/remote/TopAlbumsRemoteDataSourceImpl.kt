package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.model.TopAlbums

internal class TopAlbumsRemoteDataSourceImpl(
    private val topAlbumsService: TopAlbumsService
): TopAlbumsRemoteDataSource {
    override suspend fun fetchTopAlbums(): Result<TopAlbums> {
       return topAlbumsService.topAlbums().map { it.toTopAlbums()
       }
    }
}