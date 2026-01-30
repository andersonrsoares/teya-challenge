package br.com.teya.challenge.presentation.detail.handlers

import br.com.teya.challenge.common.event.EventHandler
import br.com.teya.challenge.common.result.fold
import br.com.teya.challenge.common.result.toUiText
import br.com.teya.challenge.presentation.detail.AlbumDetailEvent
import br.com.teya.challenge.presentation.detail.AlbumDetailStateProducer
import br.com.teya.challenge.presentation.viewstate.toViewState
import br.com.teya.challenge.topAlbums.domain.repositories.AlbumRepository

internal class OnInitAlbumDetailEventHandler(
    private val stateProducer: AlbumDetailStateProducer,
    private val repository: AlbumRepository,
): EventHandler<AlbumDetailEvent.OnInit> {
    override suspend fun process(event: AlbumDetailEvent.OnInit) {
        loadAlbum(event.albumId)
    }

    private suspend fun loadAlbum(albumId: String) {
        stateProducer.editState {
            copy(isLoading = true, isError = false)
        }
        val albums = repository.fetchAlbum(albumId)
        albums.fold(
            onSuccess = {
                stateProducer.editState {
                    copy(
                        album = it.toViewState(),
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