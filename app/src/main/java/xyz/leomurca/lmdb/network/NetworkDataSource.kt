package xyz.leomurca.lmdb.network

import xyz.leomurca.lmdb.network.model.NetworkDetails
import xyz.leomurca.lmdb.network.model.NetworkMovieResponse

interface NetworkDataSource {
    suspend fun popularMovies(): NetworkResult<NetworkMovieResponse>
    suspend fun details(id: Long): NetworkResult<NetworkDetails>
}
