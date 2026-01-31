package br.com.teya.challenge.common.event

import br.com.teya.challenge.common.state.StateProducer

class EventStateContextHolder<S, E>(
    private val stateProducer: StateProducer<S>,
    private val eventDispatcher: EventDispatcher<E>,
    private val eventSource: EventSource<E>,
    private val eventCoroutineContext: EventCoroutineContext,
): EventDispatcher<E> by eventDispatcher,
    EventCoroutineContext by eventCoroutineContext,
    EventSource<E> by eventSource,
    StateProducer<S> by stateProducer