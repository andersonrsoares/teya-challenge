package br.com.teya.challenge.common.event

import kotlinx.coroutines.CoroutineScope

interface EventCoroutineScope {
    val scope: CoroutineScope
}

class EventCoroutineScopeDelegate(
    override val scope: CoroutineScope
): EventCoroutineScope