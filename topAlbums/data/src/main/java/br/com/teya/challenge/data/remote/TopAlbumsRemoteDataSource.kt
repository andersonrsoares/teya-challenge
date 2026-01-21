package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.models.TopAlbumsFeed

interface TopAlbumsRemoteDataSource {
    suspend fun fetchTopAlbums() : Result<TopAlbumsFeed>
}