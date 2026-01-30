package br.com.teya.challenge.presentation.list.handlers


internal data class TopAlbumsListEventHandlerHolder(
    val onInitTopAlbumsListEventHandler: OnInitTopAlbumsListEventHandler,
    val onRetryTopAlbumsListEventHandler: OnRetryTopAlbumsListEventHandler,
)