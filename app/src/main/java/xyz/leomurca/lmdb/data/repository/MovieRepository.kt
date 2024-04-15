package xyz.leomurca.lmdb.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.leomurca.lmdb.data.model.Details
import xyz.leomurca.lmdb.data.model.Movie

interface MovieRepository {
    fun popularMovies(): Flow<List<Movie>>

    fun details(id: Long): Flow<Details>
}
