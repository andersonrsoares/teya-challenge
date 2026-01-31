package br.com.teya.challenge.common.event

import br.com.teya.challenge.common.state.StateProducer

class EventStateContext<S, E>(
    private val stateProducer: StateProducer<S>,
    private val eventDispatcher: EventDispatcher<E>,
    private val eventConsumer: EventConsumer<E>,
    private val eventCoroutineContext: EventCoroutineContext,
): EventDispatcher<E> by eventDispatcher,
    EventCoroutineContext by eventCoroutineContext,
    EventConsumer<E> by eventConsumer,
    StateProducer<S> by stateProducer