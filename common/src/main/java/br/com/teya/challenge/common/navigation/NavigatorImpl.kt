package br.com.teya.challenge.common.navigation

import androidx.navigation3.runtime.NavBackStack

class NavigatorImpl(
    val backStack: NavBackStack<NavigatorKey>,
): Navigator {

    override fun navigateBack() {
        backStack.removeLastOrNull()
    }

    override fun navigateTo(key: NavigatorKey) {
        backStack.add(key)
    }

    override fun navigateToRoot(key: NavigatorKey) {
        backStack[0] = key
    }
}
