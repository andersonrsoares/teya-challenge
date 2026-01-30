package br.com.teya.challenge.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named
import org.koin.dsl.ScopeDSL


@OptIn(KoinExperimentalAPI::class)
fun ScopeDSL.viewModelCoroutineScope() {
    scoped<CoroutineScope>(ViewModelCoroutineScope.Qualifier) {
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
    }
}

object ViewModelCoroutineScope {
    val Qualifier = named("viewModelCoroutineScope")
}

