package br.com.teya.challenge.common.navigation

import androidx.compose.runtime.Immutable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

typealias EntryProviderBuilder = EntryProviderScope<NavKey>.() -> Unit

@Immutable
data class NavigationHolder(
    val entryBuilders: Set<EntryProviderBuilder>,
    val navigator: Navigator,
    val backStack: NavBackStack<NavKey>,
)
