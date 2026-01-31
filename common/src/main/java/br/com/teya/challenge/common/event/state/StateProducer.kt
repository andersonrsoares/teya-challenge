package br.com.teya.challenge.common.event.state

interface StateProducer<T> {
    fun editState(newState: T.() -> T)
}
