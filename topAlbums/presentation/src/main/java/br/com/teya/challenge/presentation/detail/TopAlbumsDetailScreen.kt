package br.com.teya.challenge.presentation.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.teya.challenge.common.composables.CustomScaffold


@Composable
internal fun TopAlbumsDetailScreen(
    viewModel: TopAlbumsDetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(TopAlbumsDetailEvent.OnLaunched)
    }

    TopAlbumsListScreen(
        uiState = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAlbumsListScreen(
    uiState: TopAlbumsDetailState,
    onEvent: (TopAlbumsDetailEvent) -> Unit,
) {
    CustomScaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.album?.name.orEmpty()) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(TopAlbumsDetailEvent.OnNavigateBack)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                modifier =
                    Modifier
                        .shadow(
                            shape = RectangleShape,
                            elevation = 10.dp,
                        ),
            )
        },
    ) { padding ->

    }
}

