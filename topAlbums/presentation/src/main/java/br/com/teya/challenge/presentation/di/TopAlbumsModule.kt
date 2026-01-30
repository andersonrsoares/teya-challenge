package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.topAlbums.data.di.TopAlbumsDataModule


val TopAlbumsModule = TopAlbumsPresentationModule + TopAlbumsDataModule
