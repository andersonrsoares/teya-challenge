package br.com.teya.challenge.domain.di

import br.com.teya.challenge.domain.AlbumRepository
import br.com.teya.challenge.domain.AlbumRepositoryImpl
import br.com.teya.challenge.domain.TopAlbumsRepository
import br.com.teya.challenge.domain.TopAlbumsRepositoryImpl
import org.koin.dsl.module


val TopAlbumsDomainModule = module {
    factory {
        AlbumRepositoryImpl(get()) as AlbumRepository
    }
    factory {
        TopAlbumsRepositoryImpl(get(), get()) as TopAlbumsRepository
    }
}