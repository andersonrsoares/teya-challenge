package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.common.di.eventCoroutineScope
import br.com.teya.challenge.common.event.EventSource
import br.com.teya.challenge.common.event.EventDispatcher
import br.com.teya.challenge.common.event.EventDispatcherDelegate
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.state.StateProducerDelegate
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
    EventConsumer(named("AlbumDetailEventConsumer")),
}


@OptIn(KoinExperimentalAPI::class)
internal val AlbumDetailViewModelModule = module {
    viewModelScope {
        eventCoroutineScope(
            qualifier = AlbumDetailQualifier.EventCoroutineScope.qualifier
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

        scoped<AlbumDetailStateProducer> {
            AlbumDetailStateProducer(
                producer = StateProducerDelegate(
                    initialState = AlbumDetailState(),
                )
            )
        }

        scoped<EventDispatcherDelegate<AlbumDetailEvent>>(AlbumDetailQualifier.EventDispatcherDelegate.qualifier) {
            EventDispatcherDelegate(
                eventCoroutineContext = get(AlbumDetailQualifier.EventCoroutineScope.qualifier)
            )
        }

        scoped(AlbumDetailQualifier.EventDispatcher.qualifier) {
            get<EventDispatcherDelegate<AlbumDetailEvent>>(AlbumDetailQualifier.EventDispatcherDelegate.qualifier) as EventDispatcher<AlbumDetailEvent>
        }

        scoped(AlbumDetailQualifier.EventConsumer.qualifier) {
            get<EventDispatcherDelegate<AlbumDetailEvent>>(AlbumDetailQualifier.EventDispatcherDelegate.qualifier) as EventSource<AlbumDetailEvent>
        }

        scoped(AlbumDetailQualifier.EventContext.qualifier) {
            EventStateContext<AlbumDetailState, AlbumDetailEvent>(
                stateProducer = get<AlbumDetailStateProducer>(),
                eventDispatcher = get(AlbumDetailQualifier.EventDispatcher.qualifier),
                eventSource = get(AlbumDetailQualifier.EventConsumer.qualifier),
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
