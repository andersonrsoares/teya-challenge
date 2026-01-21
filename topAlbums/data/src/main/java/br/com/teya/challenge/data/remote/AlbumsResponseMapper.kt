package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.model.Album
import br.com.teya.challenge.data.model.AlbumImage
import br.com.teya.challenge.data.model.TopAlbumsFeed
import br.com.teya.challenge.data.remote.response.AlbumEntryResponse
import br.com.teya.challenge.data.remote.response.AlbumImageResponse
import br.com.teya.challenge.data.remote.response.TopAlbumsResponse


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