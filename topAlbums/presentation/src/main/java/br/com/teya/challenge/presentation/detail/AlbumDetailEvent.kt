package br.com.teya.challenge.presentation.detail


internal sealed class AlbumDetailEvent {
    data class OnInit(val albumId: String): AlbumDetailEvent()
    data object OnNavigateBack: AlbumDetailEvent()
    data class OpenAppleStoreLink(val url: String): AlbumDetailEvent()
}