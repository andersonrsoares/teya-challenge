package br.com.teya.challenge.topAlbums.domain.repositories

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.topAlbums.domain.models.Album

interface AlbumRepository {
    suspend fun fetchAlbum(id: String) : DataStateResult<Album>
}