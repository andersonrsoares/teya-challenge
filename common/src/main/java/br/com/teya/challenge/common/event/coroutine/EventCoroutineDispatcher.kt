package br.com.teya.challenge.common.event.coroutine

import kotlinx.coroutines.CoroutineDispatcher

data class EventCoroutineDispatcher(
    val dispatchOn: CoroutineDispatcher,
    val collectOn: CoroutineDispatcher,
    val handleOn: CoroutineDispatcher,
)