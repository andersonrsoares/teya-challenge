package br.com.teya.challenge.common.event

import kotlinx.coroutines.flow.SharedFlow

interface EventConsumer<E> {
    val eventFlow: SharedFlow<E>
}