package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.model.TopAlbums
import br.com.teya.challenge.network.result.RemoteDataSourceResult

interface TopAlbumsRemoteDataSource {
    suspend fun fetchTopAlbums() : RemoteDataSourceResult<TopAlbums>
}