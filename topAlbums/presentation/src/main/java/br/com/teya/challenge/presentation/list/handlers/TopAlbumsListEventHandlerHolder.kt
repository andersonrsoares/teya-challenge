package br.com.teya.challenge.presentation.list.handlers


internal data class TopAlbumsListEventHandlerHolder(
    val onInit: OnInitTopAlbumsListEventHandler,
    val onRetry: OnRetryTopAlbumsListEventHandler,
)