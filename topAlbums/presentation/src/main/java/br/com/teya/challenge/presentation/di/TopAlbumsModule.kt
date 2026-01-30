package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.topAlbums.data.di.TopAlbumsDataModule
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
val TopAlbumsPresentationModule = TopAlbumsListViewModelModule + AlbumDetailViewModelModule

val TopAlbumsModule = TopAlbumsPresentationModule + TopAlbumsDataModule
