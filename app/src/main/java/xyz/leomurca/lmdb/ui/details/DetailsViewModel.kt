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
import xyz.leomurca.lmdb.di.MovieId
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    movieRepository: MovieRepository,
    @MovieId private val movieId: Long,
) : ViewModel() {

    val uiState: StateFlow<UiState> =
        movieRepository.details(movieId).map {
            UiState.Loaded(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading,
        )

    sealed interface UiState {
        data object Loading : UiState

        data class Loaded(val details: Details) : UiState
    }
}
