package br.com.teya.challenge.common.event.state

import kotlinx.coroutines.flow.StateFlow

interface StateConsumer<T> {
    val state: StateFlow<T>
}
