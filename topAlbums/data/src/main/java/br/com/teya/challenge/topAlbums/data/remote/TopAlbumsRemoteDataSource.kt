package br.com.teya.challenge.topAlbums.data.remote

import br.com.teya.challenge.topAlbums.domain.models.TopAlbumsFeed

interface TopAlbumsRemoteDataSource {
    suspend fun fetchTopAlbums() : Result<TopAlbumsFeed>
}