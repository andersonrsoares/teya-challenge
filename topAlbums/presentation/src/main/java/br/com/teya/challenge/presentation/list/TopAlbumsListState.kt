package br.com.teya.challenge.presentation.list

import androidx.compose.runtime.Immutable
import br.com.teya.challenge.common.composables.UiText
import br.com.teya.challenge.presentation.viewstate.AlbumViewState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
internal data class TopAlbumsListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: UiText? = null,
    val topAlbums: ImmutableList<AlbumViewState> = persistentListOf(),
)