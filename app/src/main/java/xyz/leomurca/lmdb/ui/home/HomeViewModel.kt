package xyz.leomurca.lmdb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import xyz.leomurca.lmdb.data.model.Movie
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.MovieResult
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository,
) : ViewModel() {

    val uiState: StateFlow<UiState> =
        movieRepository.popularMovies().map {
            when (it) {
                is MovieResult.Success -> UiState.Loaded.Success(it.data)
                is MovieResult.Error -> UiState.Loaded.Error(it.message)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading,
        )

    sealed interface UiState {
        data object Loading : UiState

        sealed class Loaded : UiState {
            data class Success(val movies: List<Movie>) : Loaded()
            data class Error(val message: String) : Loaded()
        }
    }
}
