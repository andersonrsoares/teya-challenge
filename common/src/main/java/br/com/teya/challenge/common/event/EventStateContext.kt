package br.com.teya.challenge.common.event

import br.com.teya.challenge.common.state.StateProducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class EventStateContext<S, E>(
    private val stateProducer: StateProducer<S>,
    private val eventDispatcher: EventDispatcher<E>,
    private val eventConsumer: EventConsumer<E>,
    private val eventCoroutineScope: EventCoroutineScope,
): EventDispatcher<E> by eventDispatcher,
    EventCoroutineScope by eventCoroutineScope,
    EventConsumer<E> by eventConsumer,
    StateProducer<S> by stateProducer