package xyz.leomurca.lmdb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import xyz.leomurca.lmdb.data.model.Movie
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.MovieResult
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = movieRepository.popularMovies().map { result ->
                when (result) {
                    is MovieResult.Success -> UiState.Loaded.Success(result.data)
                    is MovieResult.Error -> UiState.Loaded.Error(result.message)
                }
            }.first()
        }
    }

    fun onSearchTextChange(query: String) {
        _searchText.value = query
        viewModelScope.launch {
            _uiState.value = movieRepository.searchMovie(_searchText.value).map { result ->
                when (result) {
                    is MovieResult.Success -> UiState.Loaded.Success(result.data)
                    is MovieResult.Error -> UiState.Loaded.Error(result.message)
                }
            }.first()
        }
    }

    sealed interface UiState {
        data object Loading : UiState

        sealed class Loaded : UiState {
            data class Success(val movies: List<Movie>) : Loaded()
            data class Error(val message: String) : Loaded()
        }
    }
}
