package br.com.teya.challenge.presentation.list

import br.com.teya.challenge.common.event.state.StateProducer
import br.com.teya.challenge.common.event.state.StateProducerDelegate


internal class TopAlbumsListStateProducer(
    producer: StateProducerDelegate<TopAlbumsListState>
): StateProducer<TopAlbumsListState> by producer