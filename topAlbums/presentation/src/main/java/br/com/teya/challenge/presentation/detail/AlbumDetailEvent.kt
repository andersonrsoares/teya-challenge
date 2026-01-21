package br.com.teya.challenge.presentation.detail


internal sealed class AlbumDetailEvent {
    data object OnInit: AlbumDetailEvent()
    data object OnNavigateBack: AlbumDetailEvent()
    data class OpenAppleStoreLink(val url: String): AlbumDetailEvent()
}