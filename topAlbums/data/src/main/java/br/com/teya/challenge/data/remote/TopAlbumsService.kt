package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.remote.response.TopAlbumsResponse
import br.com.teya.challenge.network.result.RemoteDataSourceResult
import retrofit2.http.GET

internal interface TopAlbumsService {

    @GET("topalbums/limit=100/json")
    suspend fun topAlbums(): RemoteDataSourceResult<TopAlbumsResponse>
}