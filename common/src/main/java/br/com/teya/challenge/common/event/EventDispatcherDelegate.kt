package br.com.teya.challenge.common.event

import kotlinx.coroutines.launch


class EventDispatcherDelegate<E>(
    eventCoroutineContext: EventCoroutineContext,
    private val eventSource: EventSource<E>,
): EventDispatcher<E>,
    EventCoroutineContext by eventCoroutineContext {

    override fun onEvent(event: E) {
        scope.launch(coroutineDispatcher.dispatchOn) {
            eventSource.emit(event)
        }
    }
}