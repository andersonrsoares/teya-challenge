package br.com.teya.challenge.presentation.list


internal sealed class TopAlbumsListEvent {
    data object OnLaunched: TopAlbumsListEvent()
    data class OnNavigateToAlbumDetails(val albumId: String): TopAlbumsListEvent()
}