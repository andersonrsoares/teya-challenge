package br.com.teya.challenge.presentation.di

import br.com.teya.challenge.data.di.TopAlbumsDataModule
import br.com.teya.challenge.domain.di.TopAlbumsDomainModule


val TopAlbumsModule = TopAlbumsDataModule + TopAlbumsDomainModule + TopAlbumsPresentationModule