package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.common.di.ViewModelCoroutineScope
import br.com.teya.challenge.common.di.eventCoroutineScope
import br.com.teya.challenge.common.event.EventConsumer
import br.com.teya.challenge.common.event.EventCoroutineScope
import br.com.teya.challenge.common.event.EventCoroutineScopeDelegate
import br.com.teya.challenge.common.event.EventDispatcher
import br.com.teya.challenge.common.event.EventDispatcherDelegate
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.state.StateProducer
import br.com.teya.challenge.common.state.StateProducerDelegate
import br.com.teya.challenge.presentation.detail.AlbumDetailEvent
import br.com.teya.challenge.presentation.detail.AlbumDetailState
import br.com.teya.challenge.presentation.detail.AlbumDetailStateProducer
import br.com.teya.challenge.presentation.detail.AlbumDetailViewModel
import br.com.teya.challenge.presentation.list.TopAlbumsListEvent
import br.com.teya.challenge.presentation.list.TopAlbumsListState
import br.com.teya.challenge.presentation.list.TopAlbumsListStateProducer
import br.com.teya.challenge.presentation.list.TopAlbumsListViewModel
import br.com.teya.challenge.topAlbums.data.di.TopAlbumsDataModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.viewmodel.scope.viewModelScope


@OptIn(KoinExperimentalAPI::class)
private val TopAlbumsListViewModelModule = module {
    viewModelScope {
        eventCoroutineScope()
        viewModel {
            TopAlbumsListViewModel(
                eventStateContext = get(named("TopAlbumsListEventContext")),
                navigator = get(),
                repository = get()
            )
        }

        scoped<TopAlbumsListStateProducer> {
            TopAlbumsListStateProducer(
                producer = StateProducerDelegate(
                    initialState = TopAlbumsListState(),
                )
            )
        }

        scoped<EventCoroutineScope> {
            EventCoroutineScopeDelegate(
                scope = get(ViewModelCoroutineScope.Qualifier)
            )
        }

        scoped<EventDispatcherDelegate<TopAlbumsListEvent>> {
            EventDispatcherDelegate(
                eventCoroutineScope = get()
            )
        }

        scoped(named("TopAlbumsListEventContext")) {
            val eventDispatcherDelegate = get<EventDispatcherDelegate<TopAlbumsListEvent>>()
            val eventDispatcher = eventDispatcherDelegate as EventDispatcher<TopAlbumsListEvent>
            val eventConsumer = eventDispatcherDelegate as EventConsumer<TopAlbumsListEvent>

            EventStateContext(
                stateProducer = get<TopAlbumsListStateProducer>(),
                eventDispatcher = eventDispatcher,
                eventConsumer = eventConsumer,
                eventCoroutineScope = get()
            )
        }
    }
}

@OptIn(KoinExperimentalAPI::class)
private val AlbumDetailViewModelModule = module {
    viewModelScope {
        eventCoroutineScope()
        viewModel { params ->
            AlbumDetailViewModel(
                eventStateContext = get(named("AlbumDetailEventContext")),
                navigator = get(),
                repository = get(),
                externalNavigator = get(),
                albumId = params.get()
            )
        }

        scoped<AlbumDetailStateProducer> {
            AlbumDetailStateProducer(
                producer = StateProducerDelegate(
                    initialState = AlbumDetailState(),
                )
            )
        }

        scoped<EventCoroutineScope> {
            EventCoroutineScopeDelegate(
                scope = get(ViewModelCoroutineScope.Qualifier)
            )
        }

        scoped<EventDispatcherDelegate<AlbumDetailEvent>> {
            EventDispatcherDelegate(
                eventCoroutineScope = get()
            )
        }

        scoped(named("AlbumDetailEventContext")) {
            val eventDispatcherDelegate = get<EventDispatcherDelegate<AlbumDetailEvent>>()
            val eventDispatcher = eventDispatcherDelegate as EventDispatcher<AlbumDetailEvent>
            val eventConsumer = eventDispatcherDelegate as EventConsumer<AlbumDetailEvent>

            EventStateContext(
                stateProducer = get<AlbumDetailStateProducer>(),
                eventDispatcher = eventDispatcher,
                eventConsumer = eventConsumer,
                eventCoroutineScope = get()
            )
        }
    }
}

@OptIn(KoinExperimentalAPI::class)
val TopAlbumsPresentationModule = TopAlbumsListViewModelModule + AlbumDetailViewModelModule + TopAlbumsDataModule

