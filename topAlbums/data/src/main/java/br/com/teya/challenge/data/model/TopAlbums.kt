package br.com.teya.challenge.data.model

data class TopAlbums(
    val feed: Feed
)

data class Feed(
    val entry: List<AlbumEntry>
)

data class AlbumEntry(
    val name: String,
    val artist: String,
    val id: String,
    val images: List<AlbumImage>,
)


data class AlbumName(
    val label: String
)


data class AlbumImage(
    val label: String,
    val attributes: ImageAttributes
)


data class ImageAttributes(
    val height: String
)


data class AlbumArtist(
    val label: String
)