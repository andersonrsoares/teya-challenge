package br.com.teya.challenge.common.event

interface EventDispatcher<E> {
    fun onEvent(event: E)
}