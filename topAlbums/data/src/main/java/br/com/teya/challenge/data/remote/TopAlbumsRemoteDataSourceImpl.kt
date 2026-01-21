package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.models.TopAlbumsFeed
import br.com.teya.challenge.data.remote.model.mappers.toTopAlbums

internal class TopAlbumsRemoteDataSourceImpl(
    private val topAlbumsService: TopAlbumsService
): TopAlbumsRemoteDataSource {
    override suspend fun fetchTopAlbums(): Result<TopAlbumsFeed> {
       return topAlbumsService.topAlbums().map { it.toTopAlbums() }
    }
}