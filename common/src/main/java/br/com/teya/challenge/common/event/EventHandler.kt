package br.com.teya.challenge.common.event

interface EventHandler<E> {
    suspend fun process(event: E)
}