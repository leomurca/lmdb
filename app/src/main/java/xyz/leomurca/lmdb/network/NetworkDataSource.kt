package xyz.leomurca.lmdb.network

import xyz.leomurca.lmdb.network.model.NetworkMovieResponse

interface NetworkDataSource {
    suspend fun getPopularMovies(): NetworkMovieResponse
}
