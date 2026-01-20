package br.com.teya.challenge.presentation.list

import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.result.fold
import br.com.teya.challenge.common.viewmodel.EventViewModel
import br.com.teya.challenge.domain.TopAlbumsRepository
import kotlinx.collections.immutable.toPersistentList

internal class TopAlbumsListViewModel(
    stateProducer: TopAlbumsListStateProducer,
    navigator: Navigator,
    val repository: TopAlbumsRepository,
): EventViewModel<TopAlbumsListState, TopAlbumsListEvent>(
     stateProducer = stateProducer,
) {

    override suspend fun onCollect(event: TopAlbumsListEvent) {
        when (event) {
            is TopAlbumsListEvent.OnLaunched -> {
                loadTopAlbums()
            }
            is TopAlbumsListEvent.OnNavigateToAlbumDetails -> {

            }
        }
    }

    private suspend fun loadTopAlbums() {
        editState {
            copy(isLoading = true)
        }
        val albums = repository.fetchTopAlbums()
        albums.fold(
            onSuccess = {
                editState {
                    copy(
                        topAlbums = it.feed.entry.toPersistentList(),
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