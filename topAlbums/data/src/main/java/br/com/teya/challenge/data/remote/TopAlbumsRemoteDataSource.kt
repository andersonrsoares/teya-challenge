package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.model.TopAlbums

interface TopAlbumsRemoteDataSource {
    suspend fun fetchTopAlbums() : Result<TopAlbums>
}