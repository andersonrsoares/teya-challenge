package br.com.teya.challenge.presentation.list.handlers

import br.com.teya.challenge.common.event.EventDispatcher
import br.com.teya.challenge.common.event.EventHandler
import br.com.teya.challenge.presentation.list.TopAlbumsListEvent


internal class OnRetryTopAlbumsListEventHandler(
    private val eventDispatcher: EventDispatcher<TopAlbumsListEvent>,
): EventHandler<TopAlbumsListEvent.OnRetry> {
    override suspend fun process(event: TopAlbumsListEvent.OnRetry) {
        eventDispatcher.onEvent(TopAlbumsListEvent.OnInit)
    }
}