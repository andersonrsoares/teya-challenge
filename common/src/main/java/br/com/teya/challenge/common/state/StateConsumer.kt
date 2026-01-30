package br.com.teya.challenge.common.state

import kotlinx.coroutines.flow.StateFlow

interface StateConsumer<T> {
    val state: StateFlow<T>
}
