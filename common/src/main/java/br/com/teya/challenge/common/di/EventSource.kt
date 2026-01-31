package br.com.teya.challenge.common.di

import br.com.teya.challenge.common.event.EventSource
import br.com.teya.challenge.common.event.EventSourceFlow
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.ScopeDSL


@OptIn(KoinExperimentalAPI::class)
fun <E> ScopeDSL.eventSourceFlow(
    qualifier: Qualifier? = null,
) {
    scoped<EventSource<E>>(qualifier) {
        EventSourceFlow()
    }
}
