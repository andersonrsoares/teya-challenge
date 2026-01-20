package br.com.teya.challenge.presentation.navigation

import br.com.teya.challenge.common.navigation.NavigatorKey
import kotlinx.serialization.Serializable


@Serializable
data object TopAlbumsListScreen : NavigatorKey

@Serializable
data class TopAlbumsDetailScreen(val id: String) : NavigatorKey
