package br.com.teya.challenge.common.event

import br.com.teya.challenge.common.event.coroutine.EventCoroutineContext
import br.com.teya.challenge.common.event.dispacher.EventDispatcher
import br.com.teya.challenge.common.event.source.EventSource
import br.com.teya.challenge.common.event.state.StateConsumer
import br.com.teya.challenge.common.event.state.StateProducer

class EventStateContext<S, E>(
    private val stateProducer: StateProducer<S>,
    private val stateConsumer: StateConsumer<S>,
    private val eventDispatcher: EventDispatcher<E>,
    private val eventSource: EventSource<E>,
    private val eventCoroutineContext: EventCoroutineContext,
): EventDispatcher<E> by eventDispatcher,
    EventCoroutineContext by eventCoroutineContext,
    EventSource<E> by eventSource,
    StateProducer<S> by stateProducer,
    StateConsumer<S> by stateConsumer