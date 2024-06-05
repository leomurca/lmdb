package xyz.leomurca.lmdb.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import xyz.leomurca.lmdb.data.model.Details
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.MovieResult
import xyz.leomurca.lmdb.di.MovieId
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    movieRepository: MovieRepository,
    @MovieId private val movieId: Long,
) : ViewModel() {

    val uiState: StateFlow<UiState> =
        movieRepository.details(movieId).map {
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
            data class Success(val details: Details) : Loaded()
            data class Error(val message: String) : Loaded()
        }
    }
}
