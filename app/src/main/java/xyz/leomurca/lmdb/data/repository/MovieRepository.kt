package xyz.leomurca.lmdb.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.leomurca.lmdb.data.model.Movie

interface MovieRepository {
    fun getPopularMovies(): Flow<List<Movie>>
}
