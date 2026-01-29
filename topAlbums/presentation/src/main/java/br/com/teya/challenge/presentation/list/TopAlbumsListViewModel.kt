package br.com.teya.challenge.presentation.list

import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.result.fold
import br.com.teya.challenge.common.result.toUiText
import br.com.teya.challenge.common.viewmodel.EventViewModel
import br.com.teya.challenge.topAlbums.domain.repositories.TopAlbumsRepository
import br.com.teya.challenge.presentation.navigation.AlbumDetailScreen
import br.com.teya.challenge.presentation.viewstate.toViewState
import kotlinx.collections.immutable.toPersistentList

internal class TopAlbumsListViewModel(
    stateProducer: TopAlbumsListStateProducer,
    private val navigator: Navigator,
    private val repository: TopAlbumsRepository,
): EventViewModel<TopAlbumsListState, TopAlbumsListEvent>(
     stateProducer = stateProducer,
) {

    init {
        onEvent(TopAlbumsListEvent.OnInit)
    }

    override suspend fun onCollect(event: TopAlbumsListEvent) {
        when (event) {
            is TopAlbumsListEvent.OnInit,
               TopAlbumsListEvent.OnRetry -> {
                loadTopAlbums()
            }
            is TopAlbumsListEvent.OnNavigateToAlbumDetails -> {
                navigator.navigateTo(AlbumDetailScreen(event.albumId))
            }
        }
    }

    private suspend fun loadTopAlbums() {
        editState {
            copy(isLoading = true, isError = false)
        }
        val albums = repository.fetchTopAlbums()
        albums.fold(
            onSuccess = {
                editState {
                    copy(
                        topAlbums = it.albums.map { album -> album.toViewState() }.toPersistentList() ,
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