package br.com.teya.challenge.common.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class EventDispatcherDelegate<E>(
    eventCoroutineContext: EventCoroutineContext,
): EventDispatcher<E>, EventConsumer<E>,
    EventCoroutineContext by eventCoroutineContext {
    override val eventStream: MutableSharedFlow<E> = MutableSharedFlow()

    override fun onEvent(event: E) {
        scope.launch(coroutineDispatcher.dispatchOn) {
            eventStream.emit(event)
        }
    }
}