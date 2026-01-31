package br.com.teya.challenge.common.event


interface EventSource<E> {
    suspend fun collect(onCollect: suspend (event: E) -> Unit)
    suspend fun emit(event: E)
}