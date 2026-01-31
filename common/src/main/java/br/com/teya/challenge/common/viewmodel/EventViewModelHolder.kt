package br.com.teya.challenge.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.teya.challenge.common.event.EventCoroutineContext
import br.com.teya.challenge.common.event.EventStateContextHolder
import br.com.teya.challenge.common.event.EventDispatcher
import br.com.teya.challenge.common.state.StateProducer
import kotlinx.coroutines.launch

abstract class EventViewModelHolder<S, E: Any>(
    private val eventStateContext: EventStateContextHolder<S, E>,
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