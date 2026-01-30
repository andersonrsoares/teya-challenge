package br.com.teya.challenge.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.teya.challenge.common.event.EventCollector
import br.com.teya.challenge.common.event.EventConsumer
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
    EventCollector<E> {

    init {
        viewModelScope.launch {
            eventFlow
                .collect(::collectEvent)
        }.invokeOnCompletion {
            println("EventContext collect event invokeOnCompletion: ${this::class.simpleName}")
        }
    }

    private fun collectEvent(event: E) {
        viewModelScope.launch {
            onCollect(event)
        }
    }

    abstract override suspend fun onCollect(event: E)
}