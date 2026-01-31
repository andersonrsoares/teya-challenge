package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.common.di.eventCoroutineScope
import br.com.teya.challenge.common.di.eventSourceFlow
import br.com.teya.challenge.common.event.dispacher.EventDispatcher
import br.com.teya.challenge.common.event.dispacher.EventDispatcherDelegate
import br.com.teya.challenge.common.event.source.EventSource
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.event.state.StateStore
import br.com.teya.challenge.presentation.list.TopAlbumsListEvent
import br.com.teya.challenge.presentation.list.TopAlbumsListState
import br.com.teya.challenge.presentation.list.TopAlbumsListStateProducer
import br.com.teya.challenge.presentation.list.TopAlbumsListViewModel
import br.com.teya.challenge.presentation.list.handlers.OnInitTopAlbumsListEventHandler
import br.com.teya.challenge.presentation.list.handlers.OnRetryTopAlbumsListEventHandler
import br.com.teya.challenge.presentation.list.handlers.TopAlbumsListEventHandlerHolder
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.viewmodel.scope.viewModelScope

private enum class TopAlbumsListQualifier(val qualifier: Qualifier) {
    EventContext(named("TopAlbumsListEventContext")),
    EventCoroutineScope(named("TopAlbumsListEventCoroutineScope")),
    EventDispatcherDelegate(named("TopAlbumsListEventDispatcherDelegate")),
    EventDispatcher(named("TopAlbumsListEventDispatcher")),
    EventSource(named("TopAlbumsListEventSource")),
    StateStore(named("TopAlbumsListStateStore")),

}

@OptIn(KoinExperimentalAPI::class)
internal val TopAlbumsListViewModelModule = module {

    viewModelScope {
        eventCoroutineScope(
            qualifier = TopAlbumsListQualifier.EventCoroutineScope.qualifier
        )
        eventSourceFlow<TopAlbumsListEvent>(
            qualifier = TopAlbumsListQualifier.EventSource.qualifier
        )
        viewModel {
            TopAlbumsListViewModel(
                eventStateContext = get(TopAlbumsListQualifier.EventContext.qualifier),
                eventHandlerHolder = get(),
                navigator = get(),
            )
        }


        scoped<StateStore<TopAlbumsListState>>(TopAlbumsListQualifier.StateStore.qualifier) {
            StateStore(
                initialState = TopAlbumsListState(),
            )
        }

        scoped<TopAlbumsListStateProducer> {
            TopAlbumsListStateProducer(
                store = get<StateStore<TopAlbumsListState>>(TopAlbumsListQualifier.StateStore.qualifier)
            )
        }


        scoped<EventDispatcherDelegate<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcherDelegate.qualifier) {
            EventDispatcherDelegate(
                eventCoroutineContext = get(TopAlbumsListQualifier.EventCoroutineScope.qualifier),
                eventSource = get<EventSource<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventSource.qualifier)
            )
        }

        scoped<EventDispatcher<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcher.qualifier) {
            get<EventDispatcherDelegate<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcherDelegate.qualifier) as EventDispatcher<TopAlbumsListEvent>
        }

        scoped(TopAlbumsListQualifier.EventContext.qualifier) {
            EventStateContext(
                stateProducer = get<TopAlbumsListStateProducer>(),
                stateConsumer = get<StateStore<TopAlbumsListState>>(TopAlbumsListQualifier.StateStore.qualifier),
                eventDispatcher = get<EventDispatcher<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcher.qualifier),
                eventSource = get<EventSource<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventSource.qualifier),
                eventCoroutineContext = get(TopAlbumsListQualifier.EventCoroutineScope.qualifier),
            )
        }

        scoped<OnRetryTopAlbumsListEventHandler> {
            OnRetryTopAlbumsListEventHandler(
                eventDispatcher = get<EventDispatcher<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcher.qualifier),
            )
        }

        scoped<OnInitTopAlbumsListEventHandler> {
            OnInitTopAlbumsListEventHandler(
                stateProducer = get<TopAlbumsListStateProducer>(),
                repository = get()
            )
        }

        scoped<TopAlbumsListEventHandlerHolder> {
            TopAlbumsListEventHandlerHolder(
                onInit = get(),
                onRetry = get(),
            )
        }
    }
}