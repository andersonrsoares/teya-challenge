package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.data.model.AlbumEntry
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

// TODO create view state instead use model class
internal data class TopAlbumsDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val album: AlbumEntry? = null,
)