package br.com.teya.challenge.topAlbums.data.local.mappers

import br.com.teya.challenge.topAlbums.data.local.database.entities.AlbumEntity
import br.com.teya.challenge.topAlbums.domain.models.Album
import br.com.teya.challenge.topAlbums.domain.models.AlbumImage

fun AlbumEntity.toEntity(): Album {
    return Album(
        id = this.id,
        name = this.name,
        artist = this.artist,
        releaseDate = this.releaseDate,
        category = this.category,
        link = this.link,
        rights = this.rights,
        image = this.image?.let {
            AlbumImage(
                label = it.label,
                height = it.height,
            )
        }
    )
}