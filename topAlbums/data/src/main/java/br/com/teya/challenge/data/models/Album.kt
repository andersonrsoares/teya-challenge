package br.com.teya.challenge.data.models


data class TopAlbumsFeed(
    val albums: List<Album>
)

data class Album(
    val id: String,
    val name: String,
    val artist: String,
    val releaseDate: String?,
    val category: String,
    val link: String?,
    val rights: String?,
    val image: AlbumImage?,
)

data class AlbumImage(
    val label: String,
    val height: Int,
)
