package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.common.event.state.StateProducer
import br.com.teya.challenge.common.event.state.StateProducerDelegate


internal class AlbumDetailStateProducer(
    producer: StateProducerDelegate<AlbumDetailState>
): StateProducer<AlbumDetailState> by producer