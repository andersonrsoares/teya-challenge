package br.com.teya.challenge.data.remote

import br.com.teya.challenge.data.model.AlbumEntry
import br.com.teya.challenge.data.model.AlbumImage
import br.com.teya.challenge.data.model.Feed
import br.com.teya.challenge.data.model.ImageAttributes
import br.com.teya.challenge.data.model.TopAlbums
import br.com.teya.challenge.data.remote.response.AlbumEntryResponse
import br.com.teya.challenge.data.remote.response.AlbumImageResponse
import br.com.teya.challenge.data.remote.response.FeedResponse
import br.com.teya.challenge.data.remote.response.ImageAttributesResponse
import br.com.teya.challenge.data.remote.response.TopAlbumsResponse


internal fun TopAlbumsResponse.toTopAlbums(): TopAlbums =
    TopAlbums(
        feed = feed.toFeed()
    )

private fun FeedResponse.toFeed(): Feed =
    Feed(
        entry = entry.map { it.toAlbumEntry() }
    )

private fun AlbumEntryResponse.toAlbumEntry(): AlbumEntry =
    AlbumEntry(
        name = name.label,
        images = images.map { it.toAlbumImage() },
        artist = artist.label,
        id = id.attributes.id
    )

private fun AlbumImageResponse.toAlbumImage(): AlbumImage =
    AlbumImage(
        label = label,
        attributes = attributes.toImageAttributes()
    )

private fun ImageAttributesResponse.toImageAttributes(): ImageAttributes =
    ImageAttributes(
        height = height
    )