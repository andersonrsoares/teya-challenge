package br.com.teya.challenge.domain

import br.com.teya.challenge.common.result.Result
import br.com.teya.challenge.data.model.AlbumEntry

interface AlbumRepository {
    suspend fun fetchAlbum(id: String) : Result<AlbumEntry>
}