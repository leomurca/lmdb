package xyz.leomurca.lmdb.ui.home

import kotlinx.coroutines.flow.MutableSharedFlow
import xyz.leomurca.lmdb.data.model.Details
import xyz.leomurca.lmdb.data.model.Movie
import xyz.leomurca.lmdb.data.repository.MovieRepository

class TestMovieRepository : MovieRepository {

    private val popularMoviesFlow = MutableSharedFlow<List<Movie>>()

    private val detailsMovieFlow = MutableSharedFlow<Details>()

    override fun popularMovies() = popularMoviesFlow

    override fun details(id: Long) = detailsMovieFlow

    /**
     * A test-only API to allow controlling the list of movies from tests.
     */
    suspend fun sendPopularMovies(movies: List<Movie>) {
        popularMoviesFlow.emit(movies)
    }

    /**
     * A test-only API to allow controlling the details of a movie from tests.
     */
    suspend fun sendDetails(details: Details) {
        detailsMovieFlow.emit(details)
    }
}
