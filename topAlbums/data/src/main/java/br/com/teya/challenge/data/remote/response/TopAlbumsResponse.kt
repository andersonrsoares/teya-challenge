package br.com.teya.challenge.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class TopAlbumsResponse(
    val feed: FeedResponse
)

@JsonClass(generateAdapter = true)
internal data class FeedResponse(
    val entry: List<AlbumEntryResponse>
)

@JsonClass(generateAdapter = true)
internal data class AlbumEntryResponse(
    @param:Json(name = "im:name") val name: AlbumNameResponse,
    @param:Json(name = "im:image") val images: List<AlbumImageResponse>,
    @param:Json(name = "im:artist") val artist: AlbumArtistResponse,
    val id: AlbumIdResponse
)

@JsonClass(generateAdapter = true)
internal data class AlbumNameResponse(
    val label: String
)

@JsonClass(generateAdapter = true)
internal data class AlbumImageResponse(
    val label: String,
    val attributes: ImageAttributesResponse
)

@JsonClass(generateAdapter = true)
internal data class ImageAttributesResponse(
    val height: String
)

@JsonClass(generateAdapter = true)
internal data class AlbumArtistResponse(
    val label: String
)

@JsonClass(generateAdapter = true)
internal data class AlbumIdResponse(
    val attributes: AlbumIdAttributesResponse
)

@JsonClass(generateAdapter = true)
internal data class AlbumIdAttributesResponse(
    @param:Json(name = "im:id") val id: String
)
