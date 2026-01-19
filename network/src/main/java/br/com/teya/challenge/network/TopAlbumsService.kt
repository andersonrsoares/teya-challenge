package br.com.teya.challenge.network

import br.com.teya.challenge.network.model.TopAlbumsResponse
import retrofit2.http.GET

interface TopAlbumsService {

    @GET("topalbums/limit=100/json")
    suspend fun topAlbums(): RemoteDataSourceResult<TopAlbumsResponse>
}