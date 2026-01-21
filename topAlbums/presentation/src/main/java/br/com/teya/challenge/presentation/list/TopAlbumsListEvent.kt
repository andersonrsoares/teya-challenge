package br.com.teya.challenge.presentation.list


internal sealed class TopAlbumsListEvent {
    data object OnInit: TopAlbumsListEvent()
    data class OnNavigateToAlbumDetails(val albumId: String): TopAlbumsListEvent()
    data object OnRetry: TopAlbumsListEvent()
}