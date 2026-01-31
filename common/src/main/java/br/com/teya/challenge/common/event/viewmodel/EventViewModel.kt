package br.com.teya.challenge.common.event.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.event.coroutine.EventCoroutineContext
import br.com.teya.challenge.common.event.dispacher.EventDispatcher
import br.com.teya.challenge.common.event.state.StateProducer
import kotlinx.coroutines.launch

abstract class EventViewModel<S, E: Any>(
    private val eventStateContext: EventStateContext<S, E>,
): ViewModel(eventStateContext.scope),
    EventDispatcher<E> by eventStateContext,
    StateProducer<S> by eventStateContext,
    EventCoroutineContext by eventStateContext {

    init {
        viewModelScope.launch(coroutineDispatcher.collectOn) {
            eventStateContext.collect(::onEventCollected)
        }
    }

    private fun onEventCollected(event: E) {
        viewModelScope.launch(coroutineDispatcher.handleOn) {
            handleEvent(event)
        }
    }

    abstract suspend fun handleEvent(event: E)
}