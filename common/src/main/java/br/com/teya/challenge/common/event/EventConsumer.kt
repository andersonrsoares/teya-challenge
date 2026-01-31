package br.com.teya.challenge.common.event

import kotlinx.coroutines.flow.Flow


interface EventConsumer<E> {
    val eventStream: Flow<E>
}