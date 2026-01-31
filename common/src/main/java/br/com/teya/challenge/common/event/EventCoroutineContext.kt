package br.com.teya.challenge.common.event

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface EventCoroutineContext {
    val scope: CoroutineScope
    val coroutineDispatcher: EventCoroutineDispatcher
}

data class EventCoroutineDispatcher(
    val dispatchOn: CoroutineDispatcher,
    val collectOn: CoroutineDispatcher,
    val handleOn: CoroutineDispatcher,
)

class EventCoroutineContextDelegate(
    override val scope: CoroutineScope,
    override val coroutineDispatcher: EventCoroutineDispatcher
): EventCoroutineContext