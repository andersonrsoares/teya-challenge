package br.com.teya.challenge.presentation.detail


internal sealed class TopAlbumsDetailEvent {
    data object OnLaunched: TopAlbumsDetailEvent()
    data object OnNavigateBack: TopAlbumsDetailEvent()
}