package br.com.teya.challenge.common.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class EventDispatcherDelegate<E>(
    eventCoroutineScope: EventCoroutineScope,
): EventDispatcher<E>, EventConsumer<E>,
    EventCoroutineScope by eventCoroutineScope {
    override val eventFlow: MutableSharedFlow<E> = MutableSharedFlow()

    override fun onEvent(event: E) {
        scope.launch {
            eventFlow.emit(event)
        }
    }
}