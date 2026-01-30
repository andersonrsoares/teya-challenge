package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.common.di.eventCoroutineScope
import br.com.teya.challenge.common.event.EventConsumer
import br.com.teya.challenge.common.event.EventDispatcher
import br.com.teya.challenge.common.event.EventDispatcherDelegate
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.state.StateProducer
import br.com.teya.challenge.common.state.StateProducerDelegate
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
    EventConsumer(named("TopAlbumsListEventConsumer")),
}

@OptIn(KoinExperimentalAPI::class)
internal val TopAlbumsListViewModelModule = module {

    viewModelScope {
        eventCoroutineScope(
            qualifier = TopAlbumsListQualifier.EventCoroutineScope.qualifier
        )
        viewModel {
            TopAlbumsListViewModel(
                eventStateContext = get(TopAlbumsListQualifier.EventContext.qualifier),
                eventHandlerHolder = get(),
                navigator = get(),
            )
        }

        scoped {
            TopAlbumsListStateProducer(
                producer = StateProducerDelegate(
                    initialState = TopAlbumsListState(),
                )
            )
        }


        scoped<EventDispatcherDelegate<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcherDelegate.qualifier) {
            EventDispatcherDelegate(
                eventCoroutineScope = get(TopAlbumsListQualifier.EventCoroutineScope.qualifier)
            )
        }

        scoped<EventDispatcher<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcher.qualifier) {
            get<EventDispatcherDelegate<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcherDelegate.qualifier) as EventDispatcher<TopAlbumsListEvent>
        }

        scoped<EventConsumer<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventConsumer.qualifier) {
            get<EventDispatcherDelegate<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcherDelegate.qualifier) as EventConsumer<TopAlbumsListEvent>
        }

        scoped(TopAlbumsListQualifier.EventContext.qualifier) {
            EventStateContext(
                stateProducer = get<TopAlbumsListStateProducer>(),
                eventDispatcher = get<EventDispatcher<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventDispatcher.qualifier),
                eventConsumer = get<EventConsumer<TopAlbumsListEvent>>(TopAlbumsListQualifier.EventConsumer.qualifier),
                eventCoroutineScope = get(TopAlbumsListQualifier.EventCoroutineScope.qualifier),
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
                onInitTopAlbumsListEventHandler = get(),
                onRetryTopAlbumsListEventHandler = get(),
            )
        }
    }
}