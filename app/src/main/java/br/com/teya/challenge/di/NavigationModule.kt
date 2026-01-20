package br.com.teya.challenge.di

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import br.com.teya.challenge.common.navigation.NavigationHolder
import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.navigation.NavigatorImpl
import br.com.teya.challenge.presentation.navigation.TopAlbumsListScreen
import br.com.teya.challenge.presentation.navigation.provideTopAlbumsEntryBuilder
import org.koin.dsl.module

val NavigationModule = module {
    single {
        NavBackStack<NavKey>(TopAlbumsListScreen)
    }
    single {
        NavigatorImpl(get()) as Navigator
    }
    single {
        NavigationHolder(
            entryBuilders = setOf(provideTopAlbumsEntryBuilder()),
            navigator = get(),
            backStack = get(),
        )
    }
}