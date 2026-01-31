package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.common.event.state.StateProducer
import br.com.teya.challenge.common.event.state.StateStore


internal class AlbumDetailStateProducer(
    store: StateStore<AlbumDetailState>
): StateProducer<AlbumDetailState> by store