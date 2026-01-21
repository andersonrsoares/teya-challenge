package br.com.teya.challenge.domain

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.data.model.TopAlbumsFeed

interface TopAlbumsRepository {
    suspend fun fetchTopAlbums() : DataStateResult<TopAlbumsFeed>
}