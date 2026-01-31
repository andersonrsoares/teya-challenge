package br.com.teya.challenge.presentation.list

import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.viewmodel.EventViewModel
import br.com.teya.challenge.presentation.list.handlers.TopAlbumsListEventHandlerHolder
import br.com.teya.challenge.presentation.navigation.AlbumDetailScreen

internal class TopAlbumsListViewModel(
    private val navigator: Navigator,
    private val eventHandlerHolder: TopAlbumsListEventHandlerHolder,
    eventStateContext: EventStateContext<TopAlbumsListState, TopAlbumsListEvent>,
): EventViewModel<TopAlbumsListState, TopAlbumsListEvent>(eventStateContext) {

    init {
        onEvent(TopAlbumsListEvent.OnInit)
    }

    override suspend fun handleEvent(event: TopAlbumsListEvent) {
        when (event) {
            is TopAlbumsListEvent.OnInit -> eventHandlerHolder.onInit.process(event)
            is TopAlbumsListEvent.OnRetry -> eventHandlerHolder.onRetry.process(event)
            is TopAlbumsListEvent.OnNavigateToAlbumDetails -> {
                navigator.navigateTo(AlbumDetailScreen(event.albumId))
            }
        }
    }
}