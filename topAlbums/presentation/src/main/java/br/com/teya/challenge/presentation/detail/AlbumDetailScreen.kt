package br.com.teya.challenge.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.teya.challenge.common.composables.Error
import br.com.teya.challenge.common.composables.Loading
import br.com.teya.challenge.common.composables.StateScreen
import br.com.teya.challenge.presentation.R
import br.com.teya.challenge.presentation.viewstate.AlbumViewState
import coil.compose.AsyncImage

@Composable
internal fun AlbumDetailScreen(
    viewModel: AlbumDetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    AlbumDetailScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun AlbumDetailScreen(
    state: AlbumDetailState,
    onEvent: (AlbumDetailEvent) -> Unit,
) {
    StateScreen(
        topBar = { TopBar(onEvent) },
        isLoading = state.isLoading,
        isError = state.isError,
        onError = {
            state.error?.let { error ->
                Error(
                    error = error,
                )
            }
        },
        onLoading = {
            Loading()
        },
        content = {
            state.album?.let { album ->
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
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

                            Text(
                                text = album.name,
                                style = MaterialTheme.typography.headlineMedium,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = album.artist,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                        InfoRow(label = stringResource( R.string.top_albums_detail_genre), value = album.category)
                        album.releaseDate?.let {
                            InfoRow(label = stringResource( R.string.top_albums_detail_release_date), value = it)

                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        album.link?.let {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    onEvent(AlbumDetailEvent.OpenAppleStoreLink(it))
                                },
                            ) {
                                Text(stringResource(R.string.top_albums_detail_link_title))
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                        }


                        album.rights?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.outline,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                    }
                }
            }
        }
    )
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    onEvent: (AlbumDetailEvent) -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.top_albums_detail_title)) },
        navigationIcon = {
            IconButton(
                onClick = {
                    onEvent(AlbumDetailEvent.OnNavigateBack)
                },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
        modifier = Modifier
            .shadow(
                shape = RectangleShape,
                elevation = 10.dp,
            ),
    )
}


@Preview(showBackground = true)
@Composable
private fun AlbumDetailScreenPreview() {
    MaterialTheme {
        AlbumDetailScreen(
            state = AlbumDetailState(
                album =  AlbumViewState(
                    id = "1",
                    name = "Album Name",
                    artist = "Artist Name",
                    releaseDate = "01/01/2024",
                    category = "Rock",
                    link = "https://music.apple.com/us/album/sour/1560754 Sour",
                    rights = "Rights Reserved",
                    image = "https://is1-ssl.mzstatic.com/image/thumb/Music111/v4/b7/21/11/b721118f-4959-1664-50a3-3333a1eb2841/source/170x170bb.jpg"
                )
            ),
            onEvent = {}
        )
    }
}
