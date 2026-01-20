package br.com.teya.challenge.data.local


import br.com.teya.challenge.data.model.AlbumEntry

interface AlbumsLocalDataSource {
    suspend fun saveAlbums(albums: List<AlbumEntry>)
    suspend fun fetchAlbum(id: String): AlbumEntry?
}