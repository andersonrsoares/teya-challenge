package br.com.teya.challenge.common.navigation


interface Navigator {
    fun navigateBack()
    fun navigateTo(key: NavigatorKey)

    fun navigateToRoot(key: NavigatorKey)
}