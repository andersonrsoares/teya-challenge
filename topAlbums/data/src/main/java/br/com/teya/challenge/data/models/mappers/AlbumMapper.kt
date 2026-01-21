package br.com.teya.challenge.data.models.mappers

import br.com.teya.challenge.data.local.database.entities.AlbumEntity
import br.com.teya.challenge.data.local.database.entities.AlbumImageEntity
import br.com.teya.challenge.data.models.Album

fun Album.toEntity(): AlbumEntity {
    return AlbumEntity(
        id = this.id,
        name = this.name,
        artist = this.artist,
        releaseDate = this.releaseDate,
        category = this.category,
        link = this.link,
        rights = this.rights,
        image = this.image?.let {
            AlbumImageEntity(
                label = it.label,
                height = it.height,
            )
        }
    )
}
