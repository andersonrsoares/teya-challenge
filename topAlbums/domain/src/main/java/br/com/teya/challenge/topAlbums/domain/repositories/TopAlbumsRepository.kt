package br.com.teya.challenge.topAlbums.domain.repositories

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.topAlbums.domain.models.TopAlbumsFeed

interface TopAlbumsRepository {
    suspend fun fetchTopAlbums() : DataStateResult<TopAlbumsFeed>
}