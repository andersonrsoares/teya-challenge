package br.com.teya.challenge.common.event.dispacher

interface EventDispatcher<E> {
    fun onEvent(event: E)
}