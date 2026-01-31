package br.com.teya.challenge.presentation.list.handlers

import br.com.teya.challenge.common.event.handler.EventHandler
import br.com.teya.challenge.common.result.fold
import br.com.teya.challenge.common.result.toUiText
import br.com.teya.challenge.presentation.list.TopAlbumsListEvent
import br.com.teya.challenge.presentation.list.TopAlbumsListStateProducer
import br.com.teya.challenge.presentation.viewstate.toViewState
import br.com.teya.challenge.topAlbums.domain.repositories.TopAlbumsRepository
import kotlinx.collections.immutable.toPersistentList


internal class OnInitTopAlbumsListEventHandler(
    private val stateProducer: TopAlbumsListStateProducer,
    private val repository: TopAlbumsRepository,
): EventHandler<TopAlbumsListEvent.OnInit> {
    override suspend fun process(event: TopAlbumsListEvent.OnInit) {
        loadTopAlbums()
    }

    private suspend fun loadTopAlbums() {
        stateProducer.editState {
            copy(isLoading = true, isError = false)
        }
        val albums = repository.fetchTopAlbums()
        albums.fold(
            onSuccess = {
                stateProducer.editState {
                    copy(
                        topAlbums = it.albums.map { album -> album.toViewState() }.toPersistentList() ,
                        isLoading = false,
                        isError = false,
                    )
                }
            },
            onFailure = {
                stateProducer.editState {
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