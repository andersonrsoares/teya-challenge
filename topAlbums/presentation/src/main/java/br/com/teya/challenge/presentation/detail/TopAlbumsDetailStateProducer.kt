package br.com.teya.challenge.presentation.detail

import br.com.teya.challenge.common.state.StateProducer
import br.com.teya.challenge.common.state.StateProducerDelegate
import br.com.teya.challenge.presentation.list.TopAlbumsListState


internal class TopAlbumsDetailStateProducer(
    producer: StateProducerDelegate<TopAlbumsDetailState>
): StateProducer<TopAlbumsDetailState> by producer