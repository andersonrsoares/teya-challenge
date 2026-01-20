package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.common.state.StateProducerDelegate
import br.com.teya.challenge.presentation.list.TopAlbumsListState
import br.com.teya.challenge.presentation.list.TopAlbumsListStateProducer
import br.com.teya.challenge.presentation.list.TopAlbumsListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.viewmodel.scope.viewModelScope

@OptIn(KoinExperimentalAPI::class)
val TopAlbumsPresentationModule = module {

    viewModelScope {
        viewModel {
            TopAlbumsListViewModel(
                stateProducer = get<TopAlbumsListStateProducer>(),
                navigator = get(),
                repository = get()
            )
        }
        scoped {
            TopAlbumsListStateProducer(
                producer = StateProducerDelegate(
                    initialState = TopAlbumsListState(),
                    dispatcher = Dispatchers.Main.immediate
                )
            )
        }
    }
}
