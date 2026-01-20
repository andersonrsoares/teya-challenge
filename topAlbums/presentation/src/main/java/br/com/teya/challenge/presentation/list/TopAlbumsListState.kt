package br.com.teya.challenge.presentation.list

import br.com.teya.challenge.data.model.AlbumEntry
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

// TODO create view state instead use model class
internal data class TopAlbumsListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val topAlbums: ImmutableList<AlbumEntry> = persistentListOf(),
)