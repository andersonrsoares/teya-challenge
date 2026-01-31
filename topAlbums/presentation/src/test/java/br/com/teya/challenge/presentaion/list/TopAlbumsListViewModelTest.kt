package br.com.teya.challenge.presentaion.list

import app.cash.turbine.test
import br.com.teya.challenge.common.event.coroutine.EventCoroutineContextDelegate
import br.com.teya.challenge.common.event.coroutine.EventCoroutineDispatcher
import br.com.teya.challenge.common.event.dispacher.EventDispatcherDelegate
import br.com.teya.challenge.common.event.EventStateContext
import br.com.teya.challenge.common.event.source.EventSourceFlow
import br.com.teya.challenge.common.navigation.Navigator
import br.com.teya.challenge.common.result.DataStateResult
import br.com.teya.challenge.common.event.state.StateStore
import br.com.teya.challenge.topAlbums.domain.models.Album
import br.com.teya.challenge.topAlbums.domain.models.AlbumImage
import br.com.teya.challenge.topAlbums.domain.models.TopAlbumsFeed
import br.com.teya.challenge.topAlbums.domain.repositories.TopAlbumsRepository
import br.com.teya.challenge.presentation.list.TopAlbumsListEvent
import br.com.teya.challenge.presentation.list.TopAlbumsListState
import br.com.teya.challenge.presentation.list.TopAlbumsListStateProducer
import br.com.teya.challenge.presentation.list.TopAlbumsListViewModel
import br.com.teya.challenge.presentation.list.handlers.OnInitTopAlbumsListEventHandler
import br.com.teya.challenge.presentation.list.handlers.TopAlbumsListEventHandlerHolder
import br.com.teya.challenge.presentation.navigation.AlbumDetailScreen
import br.com.teya.challenge.presentation.viewstate.toViewState
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TopAlbumsListViewModelTest {

    private val standardTestDispatcher = StandardTestDispatcher()
    private val stateStore = StateStore(
        initialState =  TopAlbumsListState(),

    )
    private val stateProducer: TopAlbumsListStateProducer = TopAlbumsListStateProducer(stateStore)
    private val navigator: Navigator = mockk(relaxed = true)
    private val repository: TopAlbumsRepository = mockk(relaxed = true)
    private lateinit var viewModel: TopAlbumsListViewModel

    @Before
    fun setup() {
        coEvery { repository.fetchTopAlbums() } returns DataStateResult.Success(TopAlbumsFeed(emptyList()))
        val eventSource = EventSourceFlow<TopAlbumsListEvent>()
        val eventDispatcherDelegate = EventDispatcherDelegate<TopAlbumsListEvent>(
            eventCoroutineContext = EventCoroutineContextDelegate(
                scope = TestScope(standardTestDispatcher),
                coroutineDispatcher = EventCoroutineDispatcher(
                    dispatchOn = standardTestDispatcher,
                    collectOn = standardTestDispatcher,
                    handleOn = standardTestDispatcher,
                )
            ),
            eventSource = eventSource
        )
        val eventStateContext = EventStateContext(
            stateProducer = stateProducer,
            stateConsumer = stateStore,
            eventDispatcher = eventDispatcherDelegate,
            eventSource = eventSource,
            eventCoroutineContext = eventDispatcherDelegate
        )

        val onInitTopAlbumsListEventHandler = OnInitTopAlbumsListEventHandler(
            repository = repository,
            stateProducer = stateProducer
        )
        viewModel = TopAlbumsListViewModel(
            navigator = navigator,
            eventStateContext = eventStateContext,
            eventHandlerHolder = TopAlbumsListEventHandlerHolder(
                onInit = onInitTopAlbumsListEventHandler,
                onRetry = mockk(),
            )
        )
    }


    @Test
    fun `OnInit fetch top albums and update state`() = runTest(standardTestDispatcher) {
        val expectedState = TopAlbumsListState(
            topAlbums = fakeTopAlbumsFeed.albums.map { it.toViewState() }.toPersistentList()
        )
        coEvery { repository.fetchTopAlbums() } returns DataStateResult.Success(fakeTopAlbumsFeed)

        viewModel.state.test {
            assertEquals(TopAlbumsListState(isLoading = false), awaitItem())
            assertEquals(TopAlbumsListState(isLoading = true), awaitItem())
            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun `OnNavigateToAlbumDetails navigate to album details screen`() = runTest(standardTestDispatcher) {
        viewModel.onEvent(TopAlbumsListEvent.OnNavigateToAlbumDetails("1"))
        advanceUntilIdle()
        verify { navigator.navigateTo(AlbumDetailScreen("1")) }
    }

    companion object {
        val fakeTopAlbumsFeed = TopAlbumsFeed(
            albums = listOf(
                Album(
                    id = "12345",
                    name = "Fake Album Title 1",
                    artist = "Fake Artist 1",
                    releaseDate = "January 1, 2024",
                    category = "Rock",
                    link = "https://example.com/album/12345",
                    rights = "© 2024 Fake Record Label",
                    image = AlbumImage(
                        label = "https://example.com/image_1_large.jpg",
                        height = 170
                    )
                ),
                Album(
                    id = "67890",
                    name = "Fake Album Title 2",
                    artist = "Fake Artist 2",
                    releaseDate = "February 2, 2024",
                    category = "Pop",
                    link = "https://example.com/album/67890",
                    rights = "© 2024 Another Fake Label",
                    image = AlbumImage(
                        label = "https://example.com/image_2_large.jpg",
                        height = 170
                    )
                )
            )
        )
    }
}