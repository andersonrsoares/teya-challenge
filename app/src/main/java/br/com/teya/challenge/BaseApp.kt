package br.com.teya.challenge

import android.app.Application
import br.com.teya.challenge.network.di.NetworkModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            modules(listOf(NetworkModule))
        }
    }
}