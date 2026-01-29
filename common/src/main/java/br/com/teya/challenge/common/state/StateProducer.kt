package br.com.teya.challenge.common.state

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

interface StateProducer<T> {
    val state: StateFlow<T>

    suspend fun editState(newState: T.() -> T)
}


class StateProducerDelegate<T>(
    initialState: T,
) : StateProducer<T> {

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<T> = _state.asStateFlow()

    override suspend fun editState(newState: T.() -> T) {
        _state.update(newState)
    }
}