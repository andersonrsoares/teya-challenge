package br.com.teya.challenge.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.teya.challenge.common.composables.CustomScaffold
import br.com.teya.challenge.data.model.AlbumEntry
import br.com.teya.challenge.presentation.R

@Composable
internal fun TopAlbumsListScreen(
    viewModel: TopAlbumsListViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TopAlbumsListScreen(
        uiState = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAlbumsListScreen(
    uiState: TopAlbumsListState,
    onEvent: (TopAlbumsListEvent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(TopAlbumsListEvent.OnLaunched)
    }
    CustomScaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.top_albums_list_title)) },
                navigationIcon = {},
                modifier =
                    Modifier
                        .shadow(
                            shape = RectangleShape,
                            elevation = 10.dp,
                        ),
            )
        },
    ) { padding ->
        LazyColumn(
            modifier =
                Modifier
                    .padding(padding)
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                count = uiState.topAlbums.size,
                key = { index -> uiState.topAlbums[index].id },
            ) { index ->
                val item = uiState.topAlbums[index]
                TopAlbumsCard(
                    album = item,
                    modifier =
                        Modifier.clickable(
                            onClick = {
                                onEvent(TopAlbumsListEvent.OnNavigateToAlbumDetails(item.id))
                            },
                        ),
                )
            }
        }
    }
}

@Composable
fun TopAlbumsCard(
    modifier: Modifier = Modifier,
    album: AlbumEntry,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
        ) {
            Text(text = album.artist, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text =  album.name, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

