package xyz.leomurca.lmdb.ui.home

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import xyz.leomurca.lmdb.data.model.Movie

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val movieRepository = TestMovieRepository()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(movieRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when load ViewModel - then ui state is Loading`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        // Assert
        assertEquals(HomeViewModel.UiState.Loading, viewModel.uiState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when loads ViewModel and receive list of movies - then ui state is Loaded`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        // Act
        movieRepository.sendPopularMovies(sampleMovies)

        // Assert
        assertEquals(HomeViewModel.UiState.Loaded(movies = sampleMovies), viewModel.uiState.value)
    }

    private val sampleMovies = listOf(
        Movie(
            id = 753342,
            title = "Napoleon",
            overview = "An epic that details the checkered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine.",
            originalLanguage = "en",
            posterImagePath = "image_url",
        ),
        Movie(
            id = 906126,
            title = "La sociedad de la nieve",
            overview = "On October 13, 1972, Uruguayan Air Force Flight 571, chartered to take a rugby team to Chile, crashes into a glacier in the heart of the Andes.",
            originalLanguage = "es",
            posterImagePath = "image_url",
        ),
    )
}
