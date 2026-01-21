package br.com.teya.challenge.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.teya.challenge.common.composables.CustomScaffold
import br.com.teya.challenge.common.composables.ErrorScreen
import br.com.teya.challenge.common.composables.LoadingScreen
import br.com.teya.challenge.data.model.Album
import br.com.teya.challenge.presentation.R
import br.com.teya.challenge.presentation.viewstate.AlbumViewState
import coil.compose.AsyncImage

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopAlbumsListScreen(
    viewModel: TopAlbumsListViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when  {
        state.isLoading -> {
            LoadingScreen(
                topBar = { TopBar() }
            )
        }
        state.isError -> {
            ErrorScreen(
                topBar = { TopBar() },
                error = state.error!!,
                onRetry = { viewModel.onEvent(TopAlbumsListEvent.OnRetry) }
            )
        }
        else -> {
            TopAlbumsListScreen(
                uiState = state,
                onEvent = viewModel::onEvent
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAlbumsListScreen(
    uiState: TopAlbumsListState,
    onEvent: (TopAlbumsListEvent) -> Unit,
) {
    CustomScaffold(
        topBar = { TopBar() },
    ) { padding ->
        LazyColumn(
            modifier =
                Modifier
                    .padding(padding),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                count = uiState.topAlbums.size,
                key = { index -> uiState.topAlbums[index].id },
            ) { index ->
                val item = uiState.topAlbums[index]
                AlbumCard(
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
private fun AlbumCard(
    modifier: Modifier = Modifier,
    album: AlbumViewState
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            album.image?.let {
                AsyncImage(
                    model = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = album.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = album.artist,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = album.category,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        modifier =
            Modifier
                .shadow(
                    shape = RectangleShape,
                    elevation = 10.dp,
                ),
        title = {
            Text(
                text = stringResource(R.string.top_albums_list_title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
         },
        navigationIcon = {},
    )
}



