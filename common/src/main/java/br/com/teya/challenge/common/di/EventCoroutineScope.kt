package br.com.teya.challenge.common.di

import br.com.teya.challenge.common.event.coroutine.EventCoroutineContext
import br.com.teya.challenge.common.event.coroutine.EventCoroutineContextDelegate
import br.com.teya.challenge.common.event.coroutine.EventCoroutineDispatcher
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
    scoped<EventCoroutineContext>(qualifier) {
        EventCoroutineContextDelegate(
            scope = get(ViewModelCoroutineScope.Qualifier),
            coroutineDispatcher = EventCoroutineDispatcher(
                dispatchOn = Dispatchers.Default,
                collectOn = Dispatchers.Default,
                handleOn = Dispatchers.Main.immediate,
            )
        )
    }
}

private object ViewModelCoroutineScope {
    val Qualifier = named("viewModelCoroutineScope")
}