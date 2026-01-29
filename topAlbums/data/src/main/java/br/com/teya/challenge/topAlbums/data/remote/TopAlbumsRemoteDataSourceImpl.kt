package br.com.teya.challenge.topAlbums.data.remote

import br.com.teya.challenge.topAlbums.data.remote.model.mappers.toTopAlbums
import br.com.teya.challenge.topAlbums.domain.models.TopAlbumsFeed

internal class TopAlbumsRemoteDataSourceImpl(
    private val topAlbumsService: TopAlbumsService
): TopAlbumsRemoteDataSource {
    override suspend fun fetchTopAlbums(): Result<TopAlbumsFeed> {
       return topAlbumsService.topAlbums().map { it.toTopAlbums() }
    }
}