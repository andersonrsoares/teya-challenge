package br.com.teya.challenge.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.teya.challenge.common.navigation.EntryProviderBuilder
import br.com.teya.challenge.presentation.detail.AlbumDetailScreen
import br.com.teya.challenge.presentation.detail.AlbumDetailViewModel
import br.com.teya.challenge.presentation.list.TopAlbumsListScreen
import br.com.teya.challenge.presentation.list.TopAlbumsListViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

internal fun EntryProviderScope<NavKey>.topAlbumsEntryBuilder() {
    entry<TopAlbumsListScreen> {
        val viewModel = koinViewModel<TopAlbumsListViewModel>()
        TopAlbumsListScreen(
            viewModel = viewModel
        )
    }
    entry<TopAlbumsDetailScreen> { entry ->
        val viewModel = koinViewModel<AlbumDetailViewModel>(
            parameters = { parametersOf(entry.id) }
        )
        AlbumDetailScreen(
            viewModel = viewModel
        )
    }
}

fun provideTopAlbumsEntryBuilder() : EntryProviderBuilder =
    { topAlbumsEntryBuilder() }

