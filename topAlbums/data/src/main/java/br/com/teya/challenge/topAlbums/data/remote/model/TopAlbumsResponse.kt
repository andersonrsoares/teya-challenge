package br.com.teya.challenge.topAlbums.data.remote.model

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
    val id: AlbumIdResponse,
    @param:Json(name = "im:name") val name: AlbumLabelResponse,
    @param:Json(name = "im:image") val images: List<AlbumImageResponse>,
    @param:Json(name = "im:artist") val artist: AlbumLabelResponse,
    @param:Json(name = "im:releaseDate") val releaseDate: AlbumReleaseDateResponse?,
    val category: AlbumCategoryResponse,
    val rights: AlbumLabelResponse?,
    val link: AlbumLinkResponse?,
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

@JsonClass(generateAdapter = true)
internal data class AlbumLabelResponse(
    val label: String
)

@JsonClass(generateAdapter = true)
internal data class AlbumReleaseDateResponse(
    val attributes: AlbumReleaseDateAttributesResponse?
)

@JsonClass(generateAdapter = true)
internal data class AlbumReleaseDateAttributesResponse(
    val label: String?
)

@JsonClass(generateAdapter = true)
internal data class AlbumCategoryResponse(
    val attributes: AlbumCategoryAttributesResponse
)

@JsonClass(generateAdapter = true)
internal data class AlbumCategoryAttributesResponse(
    val label: String,
    val scheme: String?
)

@JsonClass(generateAdapter = true)
internal data class AlbumLinkResponse(
    val attributes: AlbumLinkAttributesResponse?
)

@JsonClass(generateAdapter = true)
internal data class AlbumLinkAttributesResponse(
    val href: String?
)