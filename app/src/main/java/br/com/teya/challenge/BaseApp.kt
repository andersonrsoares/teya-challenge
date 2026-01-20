package br.com.teya.challenge

import android.app.Application
import br.com.teya.challenge.di.NavigationModule
import br.com.teya.challenge.di.TopAlbumsModule
import br.com.teya.challenge.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.option.viewModelScopeFactory

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            options(
                viewModelScopeFactory()
            )
            androidContext(this@BaseApp)
            modules(NavigationModule + NetworkModule + TopAlbumsModule)
        }
    }
}