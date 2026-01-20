package br.com.teya.challenge.data.di

import br.com.teya.challenge.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.data.local.AlbumsLocalDataSourceImpl
import br.com.teya.challenge.data.local.database.AlbumsDatabase
import br.com.teya.challenge.data.remote.TopAlbumsRemoteDataSource
import br.com.teya.challenge.data.remote.TopAlbumsRemoteDataSourceImpl
import br.com.teya.challenge.data.remote.TopAlbumsService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val TopAlbumsDataModule = module {

    single {
        get<Retrofit>().create(TopAlbumsService::class.java)
    }

    single {
        AlbumsDatabase.newInstance(androidContext())
    }

    single {
        get<AlbumsDatabase>().albumsDao()
    }

    factory {
        TopAlbumsRemoteDataSourceImpl(get()) as TopAlbumsRemoteDataSource
    }

    factory {
        AlbumsLocalDataSourceImpl(get()) as AlbumsLocalDataSource
    }
}