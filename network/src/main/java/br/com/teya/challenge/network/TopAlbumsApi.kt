package br.com.teya.challenge.network

import retrofit2.http.GET

interface TopAlbumsApi {

    // TODO add response class
    @GET("/topalbums/limit=100/json")
    suspend fun topAlbums(): Any
}