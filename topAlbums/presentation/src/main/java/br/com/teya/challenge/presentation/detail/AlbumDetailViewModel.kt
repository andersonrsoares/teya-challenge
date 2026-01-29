package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.common.navigation.ExternalNavigator
import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.result.fold
import br.com.teya.challenge.common.result.toUiText
import br.com.teya.challenge.common.viewmodel.EventViewModel
import br.com.teya.challenge.topAlbums.domain.repositories.AlbumRepository
import br.com.teya.challenge.presentation.viewstate.toViewState

internal class AlbumDetailViewModel(
    stateProducer: AlbumDetailStateProducer,
    private val navigator: Navigator,
    private val externalNavigator: ExternalNavigator,
    private val repository: AlbumRepository,
    private val albumId: String,
): EventViewModel<AlbumDetailState, AlbumDetailEvent>(
     stateProducer = stateProducer,
) {

    init {
        onEvent(AlbumDetailEvent.OnInit)
    }

    override suspend fun onCollect(event: AlbumDetailEvent) {
        when (event) {
            is AlbumDetailEvent.OnInit -> {
                loadAlbum()
            }
            is AlbumDetailEvent.OnNavigateBack -> {
                navigator.navigateBack()
            }
            is AlbumDetailEvent.OpenAppleStoreLink -> {
                externalNavigator.navigateToUrl(event.url)
            }
        }
    }

    private suspend fun loadAlbum() {
        editState {
            copy(isLoading = true, isError = false)
        }
        val albums = repository.fetchAlbum(albumId)
        albums.fold(
            onSuccess = {
                editState {
                    copy(
                        album = it.toViewState(),
                        isLoading = false,
                        isError = false,
                    )
                }
            },
            onFailure = {
                editState {
                    copy(
                        isLoading = false,
                        isError = true,
                        error = it.toUiText(),
                    )
                }
            }
        )
    }
}