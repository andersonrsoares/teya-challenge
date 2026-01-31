package br.com.teya.challenge.common.event.source

import kotlinx.coroutines.flow.MutableSharedFlow

class EventSourceFlow<E>(
    private val events: MutableSharedFlow<E> = MutableSharedFlow(replay = 1),
): EventSource<E> {


    override suspend fun emit(event: E) {
        events.emit(event)
    }

    override suspend fun collect(onCollect: suspend (event: E) -> Unit) {
        events.collect {
            onCollect(it)
        }
    }
}