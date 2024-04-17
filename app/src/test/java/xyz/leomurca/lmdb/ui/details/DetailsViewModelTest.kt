package xyz.leomurca.lmdb.ui.details

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import xyz.leomurca.lmdb.data.model.Details
import xyz.leomurca.lmdb.ui.home.MainDispatcherRule
import xyz.leomurca.lmdb.ui.home.TestMovieRepository

class DetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val movieRepository = TestMovieRepository()

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        viewModel = DetailsViewModel(movieRepository, sampleMovieId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when load ViewModel - then ui state is Loading`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        // Assert
        Assert.assertEquals(DetailsViewModel.UiState.Loading, viewModel.uiState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when loads ViewModel and receive details of a movie - then ui state is Loaded`() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

            // Act
            movieRepository.sendDetails(sampleMovieDetails)

            // Assert
            Assert.assertEquals(DetailsViewModel.UiState.Loaded(details = sampleMovieDetails), viewModel.uiState.value)
        }

    private val sampleMovieId = 753342L
    private val sampleMovieDetails = Details(
        title = "Napoleon",
        overview = "An epic that details the checkered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine.",
        backdropImagePath = "image_url",
        posterImagePath = "image_url",
        releaseDate = "2023-11-22",
        budget = "\$165,000,000.00",
        revenue = "\$220,597,104.00",
        )
}
