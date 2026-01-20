package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.model.TopAlbums
import br.com.teya.challenge.network.result.RemoteDataSourceResult
import br.com.teya.challenge.network.result.map

internal class TopAlbumsRemoteDataSourceImpl(
    private val topAlbumsService: TopAlbumsService
): TopAlbumsRemoteDataSource {
    override suspend fun fetchTopAlbums(): RemoteDataSourceResult<TopAlbums> {
       return topAlbumsService.topAlbums().map {
           it.toTopAlbums()
       }
    }
}