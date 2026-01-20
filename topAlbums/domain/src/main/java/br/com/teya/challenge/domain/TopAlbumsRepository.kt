package br.com.teya.challenge.domain

import br.com.teya.challenge.common.Result
import br.com.teya.challenge.data.model.TopAlbums

interface TopAlbumsRepository {
    suspend fun fetchTopAlbums() : Result<TopAlbums>
}