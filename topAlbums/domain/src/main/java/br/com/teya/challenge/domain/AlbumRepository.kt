package br.com.teya.challenge.domain

import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.data.model.Album

interface AlbumRepository {
    suspend fun fetchAlbum(id: String) : DataStateResult<Album>
}