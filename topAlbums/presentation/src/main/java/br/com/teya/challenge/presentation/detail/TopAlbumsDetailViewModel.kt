package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.result.fold
import br.com.teya.challenge.common.viewmodel.EventViewModel
import br.com.teya.challenge.domain.AlbumRepository

internal class TopAlbumsDetailViewModel(
    stateProducer: TopAlbumsDetailStateProducer,
    private val navigator: Navigator,
    private val repository: AlbumRepository,
    private val albumId: String,
): EventViewModel<TopAlbumsDetailState, TopAlbumsDetailEvent>(
     stateProducer = stateProducer,
) {
    override suspend fun onCollect(event: TopAlbumsDetailEvent) {
        when (event) {
            is TopAlbumsDetailEvent.OnLaunched -> {
                loadAlbum()
            }
            is TopAlbumsDetailEvent.OnNavigateBack -> {
                navigator.navigateBack()
            }
        }
    }

    private suspend fun loadAlbum() {
        editState {
            copy(isLoading = true)
        }
        val albums = repository.fetchAlbum(albumId)
        albums.fold(
            onSuccess = {
                editState {
                    copy(
                        album = it,
                        isLoading = false
                    )
                }
            },
            onError = {
                // TODO error state
                editState {
                    copy(isLoading = false)
                }
            }
        )
    }
}