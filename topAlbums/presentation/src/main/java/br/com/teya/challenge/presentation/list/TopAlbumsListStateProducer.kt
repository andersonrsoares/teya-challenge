package br.com.teya.challenge.presentation.list

import br.com.teya.challenge.common.state.StateProducer
import br.com.teya.challenge.common.state.StateProducerDelegate


internal class TopAlbumsListStateProducer(
    producer: StateProducerDelegate<TopAlbumsListState>
): StateProducer<TopAlbumsListState> by producer