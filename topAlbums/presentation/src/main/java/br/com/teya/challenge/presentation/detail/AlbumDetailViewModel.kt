package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.common.event.EventStateContextHolder
import br.com.teya.challenge.common.navigation.ExternalNavigator
import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.viewmodel.EventViewModelHolder
import br.com.teya.challenge.presentation.detail.handlers.AlbumDetailEventHandlerHolder

internal class AlbumDetailViewModel(
    private val navigator: Navigator,
    private val externalNavigator: ExternalNavigator,
    private val eventHandlerHolder: AlbumDetailEventHandlerHolder,
    eventStateContext: EventStateContextHolder<AlbumDetailState, AlbumDetailEvent>,
    albumId: String,
): EventViewModelHolder<AlbumDetailState, AlbumDetailEvent>(eventStateContext) {

    init {
        onEvent(AlbumDetailEvent.OnInit(albumId))
    }

    override suspend fun handleEvent(event: AlbumDetailEvent) {
        when (event) {
            is AlbumDetailEvent.OnInit -> eventHandlerHolder.onInit.process(event)
            is AlbumDetailEvent.OnNavigateBack -> {
                navigator.navigateBack()
            }
            is AlbumDetailEvent.OpenAppleStoreLink -> {
                externalNavigator.navigateToUrl(event.url)
            }
        }
    }
}