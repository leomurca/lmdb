package xyz.leomurca.lmdb.network.remote

import xyz.leomurca.lmdb.network.NetworkDataSource
import xyz.leomurca.lmdb.network.model.NetworkDetails
import xyz.leomurca.lmdb.network.model.NetworkDetailsResponse
import xyz.leomurca.lmdb.network.model.NetworkMovieResponse
import javax.inject.Inject

class RemoteNetworkDataSource @Inject constructor(
   private val apiService: ApiService
): NetworkDataSource{

    override suspend fun popularMovies(): NetworkMovieResponse {
        val response = apiService.popularMovies()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Failed to fetch data")
        }
    }

    override suspend fun details(id: Long): NetworkDetails {
        val response = apiService.detailsMovie(id)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Failed to fetch data")
        }
    }
}