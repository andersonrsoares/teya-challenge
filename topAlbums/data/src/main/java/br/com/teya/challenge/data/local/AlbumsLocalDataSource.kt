package br.com.teya.challenge.data.local


import br.com.teya.challenge.data.models.Album

interface AlbumsLocalDataSource {
    suspend fun saveAlbums(albums: List<Album>)
    suspend fun fetchAlbum(id: String): Album?
}