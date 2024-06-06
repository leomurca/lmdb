package xyz.leomurca.lmdb.network.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.leomurca.lmdb.network.model.NetworkDetails
import xyz.leomurca.lmdb.network.model.NetworkMovieResponse

interface ApiService {
    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun popularMovies(): Response<NetworkMovieResponse>

    @GET("movie/{id}?language=en-US")
    suspend fun detailsMovie(@Path("id") id: Long): Response<NetworkDetails>

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): Response<NetworkMovieResponse>
}