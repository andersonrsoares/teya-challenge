package br.com.teya.challenge.topAlbums.data.local


import br.com.teya.challenge.topAlbums.domain.models.Album

interface AlbumsLocalDataSource {
    suspend fun saveAlbums(albums: List<Album>)
    suspend fun fetchAlbum(id: String): Album?
}