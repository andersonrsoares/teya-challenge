package br.com.teya.challenge.domain

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.data.models.TopAlbumsFeed

interface TopAlbumsRepository {
    suspend fun fetchTopAlbums() : DataStateResult<TopAlbumsFeed>
}