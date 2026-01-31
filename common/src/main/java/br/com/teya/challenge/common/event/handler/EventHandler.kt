package br.com.teya.challenge.common.event.handler

interface EventHandler<E> {
    suspend fun process(event: E)
}