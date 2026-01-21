package br.com.teya.challenge.data.local.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumEntity(
    @PrimaryKey val id: String,
    val artist: String,
    val name: String,
    val releaseDate: String?,
    val category: String,
    val link: String?,
    val rights: String?,
    @Embedded
    val image: AlbumImageEntity?,
)

data class AlbumImageEntity(
    val label: String,
    val height: Int
)