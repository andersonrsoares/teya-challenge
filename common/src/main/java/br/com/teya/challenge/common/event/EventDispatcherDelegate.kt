package br.com.teya.challenge.common.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class EventDispatcherDelegate<E>(
    eventCoroutineContext: EventCoroutineContext,
): EventDispatcher<E>, EventSource<E>,
    EventCoroutineContext by eventCoroutineContext {
    override val events: MutableSharedFlow<E> = MutableSharedFlow()

    override fun onEvent(event: E) {
        scope.launch(coroutineDispatcher.dispatchOn) {
            events.emit(event)
        }
    }
}