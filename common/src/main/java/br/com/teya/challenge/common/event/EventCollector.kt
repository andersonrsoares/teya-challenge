package br.com.teya.challenge.common.event


interface EventCollector<E> {
    suspend fun onCollect(event: E)
}