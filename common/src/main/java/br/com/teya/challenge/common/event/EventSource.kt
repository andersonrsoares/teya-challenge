package br.com.teya.challenge.common.event

import kotlinx.coroutines.flow.Flow


interface EventSource<E> {
    val events: Flow<E>
}