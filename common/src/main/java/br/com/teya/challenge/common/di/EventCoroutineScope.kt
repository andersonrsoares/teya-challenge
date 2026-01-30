package br.com.teya.challenge.common.di

import br.com.teya.challenge.common.event.EventCoroutineScope
import br.com.teya.challenge.common.event.EventCoroutineScopeDelegate
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.ScopeDSL


@OptIn(KoinExperimentalAPI::class)
fun ScopeDSL.eventCoroutineScope() {
    viewModelCoroutineScope()
    scoped<EventCoroutineScope> {
        EventCoroutineScopeDelegate(
            scope = get(ViewModelCoroutineScope.Qualifier)
        )
    }
}