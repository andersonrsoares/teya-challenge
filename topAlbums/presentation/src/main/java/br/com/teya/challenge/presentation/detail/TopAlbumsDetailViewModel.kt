package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.viewmodel.EventViewModel
import br.com.teya.challenge.domain.TopAlbumsRepository

internal class TopAlbumsDetailViewModel(
    stateProducer: TopAlbumsDetailStateProducer,
    private val navigator: Navigator,
    private val repository: TopAlbumsRepository,
    private val albumId: String,
): EventViewModel<TopAlbumsDetailState, TopAlbumsDetailEvent>(
     stateProducer = stateProducer,
) {

    override suspend fun onCollect(event: TopAlbumsDetailEvent) {
        when (event) {
            is TopAlbumsDetailEvent.OnLaunched -> {
                // TODO load album detail
            }
            is TopAlbumsDetailEvent.OnNavigateBack -> {
                navigator.navigateBack()
            }
        }
    }

}