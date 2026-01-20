package br.com.teya.challenge.di

import br.com.teya.challenge.data.di.TopAlbumsDataModule
import br.com.teya.challenge.domain.di.TopAlbumsDomainModule
import br.com.teya.challenge.presentation.di.TopAlbumsPresentationModule


val TopAlbumsModule = TopAlbumsDataModule + TopAlbumsDomainModule + TopAlbumsPresentationModule