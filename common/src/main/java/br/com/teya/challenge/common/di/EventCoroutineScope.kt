package br.com.teya.challenge.common.di

import br.com.teya.challenge.common.event.EventCoroutineScope
import br.com.teya.challenge.common.event.EventCoroutineScopeDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.ScopeDSL


@OptIn(KoinExperimentalAPI::class)
fun ScopeDSL.eventCoroutineScope(
    qualifier: Qualifier? = null
) {
    factory<CoroutineScope>(ViewModelCoroutineScope.Qualifier) {
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
    }
    scoped<EventCoroutineScope>(qualifier) {
        EventCoroutineScopeDelegate(
            scope = get(ViewModelCoroutineScope.Qualifier)
        )
    }
}

private object ViewModelCoroutineScope {
    val Qualifier = named("viewModelCoroutineScope")
}