package br.com.teya.challenge.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.teya.challenge.common.event.EventCollector
import br.com.teya.challenge.common.event.EventDispatcher
import br.com.teya.challenge.common.state.StateProducer
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class EventViewModel<S, E: Any>(
    val stateProducer: StateProducer<S>,
): ViewModel(), EventDispatcher<E>, EventCollector<E>, StateProducer<S> by stateProducer {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

    private val eventFlow = MutableSharedFlow<E>()

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            eventFlow.collect(::collectEvent)
        }
    }

    private fun collectEvent(event: E) {
        viewModelScope.launch {
            onCollect(event)
        }
    }


    override fun onEvent(event: E) {
        viewModelScope.launch {
            eventFlow.emit(event)
        }
    }
}