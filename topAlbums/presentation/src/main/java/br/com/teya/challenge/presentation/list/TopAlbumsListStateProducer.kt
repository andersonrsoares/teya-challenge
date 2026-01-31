package br.com.teya.challenge.presentation.list

import br.com.teya.challenge.common.event.state.StateProducer
import br.com.teya.challenge.common.event.state.StateStore


internal class TopAlbumsListStateProducer(
    store: StateStore<TopAlbumsListState>
): StateProducer<TopAlbumsListState> by store