package br.com.teya.challenge.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopAlbumsResponse(
    val feed: Feed
)

@JsonClass(generateAdapter = true)
data class Feed(
    val entry: List<AlbumEntry>
)

@JsonClass(generateAdapter = true)
data class AlbumEntry(
    @param:Json(name = "im:name") val name: AlbumName,
    @param:Json(name = "im:image") val images: List<AlbumImage>,
    @param:Json(name = "im:artist") val artist: AlbumArtist,
    val id: AlbumId
)

@JsonClass(generateAdapter = true)
data class AlbumName(
    val label: String
)

@JsonClass(generateAdapter = true)
data class AlbumImage(
    val label: String,
    val attributes: ImageAttributes
)

@JsonClass(generateAdapter = true)
data class ImageAttributes(
    val height: String
)

@JsonClass(generateAdapter = true)
data class AlbumArtist(
    val label: String
)

@JsonClass(generateAdapter = true)
data class AlbumId(
    val attributes: AlbumIdAttributes
)

@JsonClass(generateAdapter = true)
data class AlbumIdAttributes(
    @param:Json(name = "im:id") val id: String
)
