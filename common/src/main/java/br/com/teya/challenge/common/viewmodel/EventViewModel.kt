package br.com.teya.challenge.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.teya.challenge.common.event.EventConsumer
import br.com.teya.challenge.common.event.EventCoroutineContext
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.event.EventDispatcher
import br.com.teya.challenge.common.state.StateProducer
import kotlinx.coroutines.launch

abstract class EventViewModel<S, E: Any>(
    private val eventStateContext: EventStateContext<S, E>,
): ViewModel(eventStateContext.scope),
    EventDispatcher<E> by eventStateContext,
    StateProducer<S> by eventStateContext,
    EventConsumer<E> by eventStateContext,
    EventCoroutineContext by eventStateContext {

    init {
        viewModelScope.launch(coroutineDispatcher.collectOn) {
            eventStream.collect(::onEventDispatched)
        }
    }

    private fun onEventDispatched(event: E) {
        viewModelScope.launch(coroutineDispatcher.handleOn) {
            handleEvent(event)
        }
    }

    abstract suspend fun handleEvent(event: E)
}