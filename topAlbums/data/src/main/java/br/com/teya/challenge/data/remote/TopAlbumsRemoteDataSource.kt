package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.model.TopAlbumsFeed

interface TopAlbumsRemoteDataSource {
    suspend fun fetchTopAlbums() : Result<TopAlbumsFeed>
}