package br.com.teya.challenge.common.navigation

import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.navigation3.runtime.NavBackStack

class NavigatorImpl(
    val backStack: NavBackStack<NavigatorKey>,
    val context: Context,
) : Navigator, ExternalNavigator {

    override fun navigateBack() {
        backStack.removeLastOrNull()
    }

    override fun navigateTo(key: NavigatorKey) {
        backStack.add(key)
    }

    override fun navigateToRoot(key: NavigatorKey) {
        backStack[0] = key
    }

    override fun navigateToUrl(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        customTabsIntent.launchUrl(context, url.toUri())
    }
}
