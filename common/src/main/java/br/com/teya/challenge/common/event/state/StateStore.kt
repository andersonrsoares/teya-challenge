package br.com.teya.challenge.common.event.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StateStore<T>(
    initialState: T,
) : StateProducer<T>, StateConsumer<T> {

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<T> = _state.asStateFlow()

    override fun editState(newState: T.() -> T) {
        _state.update(newState)
    }
}
