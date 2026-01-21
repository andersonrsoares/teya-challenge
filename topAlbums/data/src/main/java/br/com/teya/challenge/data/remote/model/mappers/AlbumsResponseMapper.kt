package br.com.teya.challenge.data.remote.model.mappers

import br.com.teya.challenge.data.models.Album
import br.com.teya.challenge.data.models.AlbumImage
import br.com.teya.challenge.data.models.TopAlbumsFeed
import br.com.teya.challenge.data.remote.model.AlbumEntryResponse
import br.com.teya.challenge.data.remote.model.AlbumImageResponse
import br.com.teya.challenge.data.remote.model.TopAlbumsResponse


internal fun TopAlbumsResponse.toTopAlbums(): TopAlbumsFeed =
    TopAlbumsFeed(
        albums = feed.entry.map { it.toAlbumEntry() }
    )


private fun AlbumEntryResponse.toAlbumEntry(): Album =
    Album(
        id = id.attributes.id,
        name = name.label,
        artist = artist.label,
        category = category.attributes.label,
        releaseDate = releaseDate?.attributes?.label,
        image = images.lastOrNull()?.toAlbumImage(),
        rights = rights?.label,
        link = link?.attributes?.href,
    )

private fun AlbumImageResponse.toAlbumImage(): AlbumImage =
    AlbumImage(
        label = label,
        height = attributes.height.toIntOrNull() ?: 0
    )