package br.com.teya.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.teya.challenge.common.navigation.NavigationHolder
import br.com.teya.challenge.common.navigation.NavigationRoot
import br.com.teya.challenge.ui.theme.TeyaChallengeTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    val navigationHolder: NavigationHolder = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TeyaChallengeTheme {
                NavigationRoot(
                    navigationHolder = navigationHolder
                )
            }
        }
    }
}