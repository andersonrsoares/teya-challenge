package br.com.teya.challenge.data.di

import br.com.teya.challenge.data.remote.TopAlbumsRemoteDataSource
import br.com.teya.challenge.data.remote.TopAlbumsRemoteDataSourceImpl
import br.com.teya.challenge.data.remote.TopAlbumsService
import org.koin.dsl.module
import retrofit2.Retrofit


val TopAlbumsDataModule = module {

    single {
        get<Retrofit>().create(TopAlbumsService::class.java)
    }

    factory {
        TopAlbumsRemoteDataSourceImpl(get()) as TopAlbumsRemoteDataSource
    }
}