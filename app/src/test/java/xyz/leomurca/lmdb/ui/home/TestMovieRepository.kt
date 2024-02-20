package xyz.leomurca.lmdb.ui.home

import kotlinx.coroutines.flow.MutableSharedFlow
import xyz.leomurca.lmdb.data.model.Movie
import xyz.leomurca.lmdb.data.repository.MovieRepository

class TestMovieRepository : MovieRepository {

    private val popularMoviesFlow = MutableSharedFlow<List<Movie>>()

    override fun getPopularMovies() = popularMoviesFlow

    /**
     * A test-only API to allow controlling the list of movies from tests.
     */
    suspend fun sendPopularMovies(topics: List<Movie>) {
        popularMoviesFlow.emit(topics)
    }
}
