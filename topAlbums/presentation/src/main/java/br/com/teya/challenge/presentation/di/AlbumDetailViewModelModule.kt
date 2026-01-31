package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.common.di.eventCoroutineScope
import br.com.teya.challenge.common.di.eventSourceFlow
import br.com.teya.challenge.common.event.dispacher.EventDispatcher
import br.com.teya.challenge.common.event.dispacher.EventDispatcherDelegate
import br.com.teya.challenge.common.event.source.EventSource
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.event.state.StateStore
import br.com.teya.challenge.presentation.detail.AlbumDetailEvent
import br.com.teya.challenge.presentation.detail.AlbumDetailState
import br.com.teya.challenge.presentation.detail.AlbumDetailStateProducer
import br.com.teya.challenge.presentation.detail.AlbumDetailViewModel
import br.com.teya.challenge.presentation.detail.handlers.AlbumDetailEventHandlerHolder
import br.com.teya.challenge.presentation.detail.handlers.OnInitAlbumDetailEventHandler
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.viewmodel.scope.viewModelScope

private enum class AlbumDetailQualifier(val qualifier: Qualifier) {
    EventContext(named("AlbumDetailEventContext")),
    EventCoroutineScope(named("AlbumDetailEventCoroutineScope")),
    EventDispatcherDelegate(named("AlbumDetailEventDispatcherDelegate")),
    EventDispatcher(named("AlbumDetailEventDispatcher")),
    EventSource(named("AlbumDetailEventSource")),

    StateStore(named("AlbumDetailStateStore")),

}


@OptIn(KoinExperimentalAPI::class)
internal val AlbumDetailViewModelModule = module {
    viewModelScope {
        eventCoroutineScope(
            qualifier = AlbumDetailQualifier.EventCoroutineScope.qualifier
        )
        eventSourceFlow<AlbumDetailEvent>(
            qualifier = AlbumDetailQualifier.EventSource.qualifier
        )
        viewModel { params ->
            AlbumDetailViewModel(
                eventStateContext = get(AlbumDetailQualifier.EventContext.qualifier),
                externalNavigator = get(),
                navigator = get(),
                eventHandlerHolder = get(),
                albumId = params.get()
            )
        }

        scoped<StateStore<AlbumDetailState>>(AlbumDetailQualifier.StateStore.qualifier) {
            StateStore(
                initialState = AlbumDetailState(),
            )
        }

        scoped<AlbumDetailStateProducer> {
            AlbumDetailStateProducer(
                store = get<StateStore<AlbumDetailState>>(AlbumDetailQualifier.StateStore.qualifier)
            )
        }

        scoped<EventDispatcherDelegate<AlbumDetailEvent>>(AlbumDetailQualifier.EventDispatcherDelegate.qualifier) {
            EventDispatcherDelegate(
                eventCoroutineContext = get(AlbumDetailQualifier.EventCoroutineScope.qualifier),
                eventSource = get<EventSource<AlbumDetailEvent>>(AlbumDetailQualifier.EventSource.qualifier)
            )
        }

        scoped(AlbumDetailQualifier.EventDispatcher.qualifier) {
            get<EventDispatcherDelegate<AlbumDetailEvent>>(AlbumDetailQualifier.EventDispatcherDelegate.qualifier) as EventDispatcher<AlbumDetailEvent>
        }

        scoped(AlbumDetailQualifier.EventContext.qualifier) {
            EventStateContext(
                stateProducer = get<AlbumDetailStateProducer>(),
                stateConsumer = get<StateStore<AlbumDetailState>>(AlbumDetailQualifier.StateStore.qualifier) ,
                eventDispatcher = get(AlbumDetailQualifier.EventDispatcher.qualifier),
                eventSource = get<EventSource<AlbumDetailEvent>>(AlbumDetailQualifier.EventSource.qualifier),
                eventCoroutineContext = get(AlbumDetailQualifier.EventCoroutineScope.qualifier)
            )
        }

        scoped<OnInitAlbumDetailEventHandler> {
            OnInitAlbumDetailEventHandler(
                stateProducer = get<AlbumDetailStateProducer>(),
                repository = get()
            )
        }

        scoped<AlbumDetailEventHandlerHolder> {
            AlbumDetailEventHandlerHolder(
                onInit = get(),
            )
        }
    }
}
