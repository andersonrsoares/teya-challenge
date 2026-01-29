package br.com.teya.challenge.topAlbums.data.di

import br.com.teya.challenge.topAlbums.data.local.AlbumsLocalDataSource
import br.com.teya.challenge.topAlbums.data.local.AlbumsLocalDataSourceImpl
import br.com.teya.challenge.topAlbums.data.local.database.AlbumsDatabase
import br.com.teya.challenge.topAlbums.data.remote.TopAlbumsRemoteDataSource
import br.com.teya.challenge.topAlbums.data.remote.TopAlbumsRemoteDataSourceImpl
import br.com.teya.challenge.topAlbums.data.remote.TopAlbumsService
import br.com.teya.challenge.topAlbums.data.repository.AlbumRepositoryImpl
import br.com.teya.challenge.topAlbums.data.repository.TopAlbumsRepositoryImpl
import br.com.teya.challenge.topAlbums.domain.repositories.AlbumRepository
import br.com.teya.challenge.topAlbums.domain.repositories.TopAlbumsRepository
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

    factory<TopAlbumsRemoteDataSource> {
        TopAlbumsRemoteDataSourceImpl(get())
    }

    factory<AlbumsLocalDataSource> {
        AlbumsLocalDataSourceImpl(get())
    }

    factory<AlbumRepository> {
        AlbumRepositoryImpl(get())
    }

    factory<TopAlbumsRepository> {
        TopAlbumsRepositoryImpl(get(), get())
    }
}
