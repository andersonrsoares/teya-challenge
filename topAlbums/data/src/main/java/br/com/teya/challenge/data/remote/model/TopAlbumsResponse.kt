package br.com.teya.challenge.data.remote.model

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
    @param:Json(name = "im:releaseDate") val releaseDate: AlbumReleaseDate?,
    val category: AlbumCategory,
    val rights: AlbumLabelResponse?,
    val link: AlbumLink?,
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
internal data class AlbumReleaseDate(
    val attributes: AlbumReleaseAttributes?
)

@JsonClass(generateAdapter = true)
internal data class AlbumReleaseAttributes(
    val label: String?
)

@JsonClass(generateAdapter = true)
internal data class AlbumCategory(
    val attributes: AlbumCategoryAttributes
)

@JsonClass(generateAdapter = true)
internal data class AlbumCategoryAttributes(
    val label: String,
    val scheme: String?
)

@JsonClass(generateAdapter = true)
internal data class AlbumLink(
    val attributes: AlbumLinkAttributes?
)

@JsonClass(generateAdapter = true)
internal data class AlbumLinkAttributes(
    val href: String?
)