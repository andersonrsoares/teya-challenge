package br.com.teya.challenge.data.local.mapper

import br.com.teya.challenge.data.local.database.entities.AlbumEntity
import br.com.teya.challenge.data.local.database.entities.AlbumImageEntity
import br.com.teya.challenge.data.model.AlbumEntry
import br.com.teya.challenge.data.model.AlbumImage
import br.com.teya.challenge.data.model.ImageAttributes

fun AlbumEntry.toEntity(): AlbumEntity {
    val selectedImage = this.images.lastOrNull()
    return AlbumEntity(
        name = name,
        artist = artist,
        id = id,
        image = AlbumImageEntity(
            label = selectedImage?.label.orEmpty(),
            height = selectedImage?.attributes?.height.orEmpty(),
        )
    )
}

fun AlbumEntity.toEntity(): AlbumEntry {
    return AlbumEntry(
        name = this.name,
        artist = this.artist,
        id = this.id,
        images = listOf(
            AlbumImage(
                label = this.image.label,
                attributes = ImageAttributes(
                    height = this.image.height
                )
            )
        )
    )
}
