package br.com.teya.challenge.domain.di

import br.com.teya.challenge.domain.TopAlbumsRepository
import br.com.teya.challenge.domain.TopAlbumsRepositoryImpl
import org.koin.dsl.module


val TopAlbumsDomainModule = module {
    factory {
        TopAlbumsRepositoryImpl(get()) as TopAlbumsRepository
    }
}