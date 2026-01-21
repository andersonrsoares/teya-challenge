package br.com.teya.challenge.presentation.detail

import androidx.compose.runtime.Immutable
import br.com.teya.challenge.common.composables.UiText
import br.com.teya.challenge.presentation.viewstate.AlbumViewState

@Immutable
internal data class AlbumDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val album: AlbumViewState? = null,
    val error: UiText? = null,
)

