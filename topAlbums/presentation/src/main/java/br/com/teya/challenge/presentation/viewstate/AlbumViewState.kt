package br.com.teya.challenge.presentation.viewstate

import androidx.compose.runtime.Immutable
import br.com.teya.challenge.topAlbums.domain.models.Album

@Immutable
internal data class AlbumViewState(
    val id: String,
    val name: String,
    val artist: String,
    val releaseDate: String?,
    val category: String,
    val link: String?,
    val rights: String?,
    val image: String?,
)


internal fun Album.toViewState(): AlbumViewState {
    return AlbumViewState(
        id = id,
        name = name,
        artist = artist,
        releaseDate = releaseDate,
        category = category,
        link = link,
        rights = rights,
        image = image?.label
    )
}