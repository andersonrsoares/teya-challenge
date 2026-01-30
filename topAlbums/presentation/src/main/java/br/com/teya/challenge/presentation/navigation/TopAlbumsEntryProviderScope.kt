package br.com.teya.challenge.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.teya.challenge.common.navigation.EntryProviderBuilder
import br.com.teya.challenge.presentation.detail.AlbumDetailScreen
import br.com.teya.challenge.presentation.list.TopAlbumsListScreen

internal fun EntryProviderScope<NavKey>.topAlbumsEntryBuilder() {
    entry<TopAlbumsListScreen> {
        TopAlbumsListScreen()
    }
    entry<AlbumDetailScreen> { entry ->
        AlbumDetailScreen(
            albumId = entry.id,
        )
    }
}

fun provideTopAlbumsEntryBuilder() : EntryProviderBuilder =
    { topAlbumsEntryBuilder() }

