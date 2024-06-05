package xyz.leomurca.lmdb.network.remote

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import retrofit2.http.HTTP
import xyz.leomurca.lmdb.network.HttpStatus
import xyz.leomurca.lmdb.network.NetworkDataSource
import xyz.leomurca.lmdb.network.NetworkResult
import xyz.leomurca.lmdb.network.model.NetworkDetails
import xyz.leomurca.lmdb.network.model.NetworkDetailsResponse
import xyz.leomurca.lmdb.network.model.NetworkError
import xyz.leomurca.lmdb.network.model.NetworkMovieResponse
import javax.inject.Inject

class RemoteNetworkDataSource @Inject constructor(
    private val apiService: ApiService,
    private val json: Json
) : NetworkDataSource {

    override suspend fun popularMovies(): NetworkResult<NetworkMovieResponse> {
        return try {
            val response = apiService.popularMovies()

            if (response.isSuccessful) {
                return NetworkResult.Success(response.body()
                    ?: throw Exception("Empty response body"))
            } else {
                val errorBody = response.errorBody()?.string() ?: ""
                val networkError = json.decodeFromString<NetworkError>(errorBody)
                when (response.code()) {
                    HttpStatus.UNAUTHORIZED.code -> NetworkResult.Error(NetworkResult.NetworkException.UnauthorizedException(networkError.message))
                    else -> NetworkResult.Error(NetworkResult.NetworkException.UnknownException("I do not know"))
                }
            }
        } catch (e: Exception) {
            NetworkResult.Error(NetworkResult.NetworkException.UnknownException(e.message.toString(), e))
        }
    }

    override suspend fun details(id: Long): NetworkResult<NetworkDetails> {
        return try {
            val response = apiService.detailsMovie(id)

            if (response.isSuccessful) {
                return NetworkResult.Success(response.body()
                    ?: throw Exception("Empty response body"))
            } else {
                val errorBody = response.errorBody()?.string() ?: ""
                val networkError = json.decodeFromString<NetworkError>(errorBody)
                when (response.code()) {
                    HttpStatus.UNAUTHORIZED.code -> NetworkResult.Error(NetworkResult.NetworkException.UnauthorizedException(networkError.message))
                    else -> NetworkResult.Error(NetworkResult.NetworkException.UnknownException("I do not know"))
                }
            }
        } catch (e: Exception) {
            NetworkResult.Error(NetworkResult.NetworkException.UnknownException(e.message.toString(), e))
        }
    }
}