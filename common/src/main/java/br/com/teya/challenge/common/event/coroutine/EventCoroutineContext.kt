package br.com.teya.challenge.common.event.coroutine

import kotlinx.coroutines.CoroutineScope

interface EventCoroutineContext {
    val scope: CoroutineScope
    val coroutineDispatcher: EventCoroutineDispatcher
}


class EventCoroutineContextDelegate(
    override val scope: CoroutineScope,
    override val coroutineDispatcher: EventCoroutineDispatcher
): EventCoroutineContext