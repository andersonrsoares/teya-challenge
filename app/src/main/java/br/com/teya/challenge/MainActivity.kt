package br.com.teya.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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