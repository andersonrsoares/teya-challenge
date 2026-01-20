package br.com.teya.challenge.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.teya.challenge.common.navigation.EntryProviderBuilder
import br.com.teya.challenge.presentation.list.TopAlbumsListScreen
import br.com.teya.challenge.presentation.list.TopAlbumsListViewModel
import org.koin.compose.viewmodel.koinViewModel

internal fun EntryProviderScope<NavKey>.topAlbumsEntryBuilder() {
    entry<TopAlbumsLisScreen> {
        val viewModel = koinViewModel<TopAlbumsListViewModel>()
        TopAlbumsListScreen(
            viewModel = viewModel
        )
    }
}

fun provideTopAlbumsEntryBuilder() : EntryProviderBuilder = {
    topAlbumsEntryBuilder()
}