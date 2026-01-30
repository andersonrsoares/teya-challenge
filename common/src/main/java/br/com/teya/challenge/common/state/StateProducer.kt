package br.com.teya.challenge.common.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface StateProducer<T>: StateConsumer<T>  {
    override val state: StateFlow<T>

    fun editState(newState: T.() -> T)
}

class StateProducerDelegate<T>(
    initialState: T,
) : StateProducer<T> {

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<T> = _state.asStateFlow()

    override fun editState(newState: T.() -> T) {
        _state.update(newState)
    }
}