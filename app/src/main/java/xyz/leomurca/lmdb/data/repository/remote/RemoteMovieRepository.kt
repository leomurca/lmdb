package xyz.leomurca.lmdb.data.repository.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import xyz.leomurca.lmdb.BuildConfig
import xyz.leomurca.lmdb.data.model.Details
import xyz.leomurca.lmdb.data.model.Movie
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.MovieResult
import xyz.leomurca.lmdb.di.AppDispatchers
import xyz.leomurca.lmdb.di.Dispatcher
import xyz.leomurca.lmdb.network.NetworkDataSource
import xyz.leomurca.lmdb.network.NetworkResult
import xyz.leomurca.lmdb.utils.DateFormatter
import xyz.leomurca.lmdb.utils.DatePattern
import xyz.leomurca.lmdb.utils.NumberFormatter

class RemoteMovieRepository(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: NetworkDataSource,
    private val dateFormatter: DateFormatter,
    private val numberFormatter: NumberFormatter,
) : MovieRepository {
    override fun popularMovies(): Flow<MovieResult<List<Movie>>> = flow {
        when (val result = dataSource.popularMovies()) {
            is NetworkResult.Success -> emit(
                MovieResult.Success(
                    result.data.results.map {
                        Movie(
                            id = it.id,
                            title = it.title,
                            overview = it.overview,
                            originalLanguage = originalLanguageOrBlank(it.originalLanguage),
                            posterImagePath = "${BuildConfig.IMAGE_BASE_URL}${POSTER_IMAGE_DIMENSIONS}${it.posterPath}",
                        )
                    }
                )
            )

            is NetworkResult.Error -> emit(
                MovieResult.Error(result.exception.message)
            )
        }
    }.flowOn(ioDispatcher)

    override fun details(id: Long): Flow<MovieResult<Details>> = flow {
        when (val result = dataSource.details(id)) {
            is NetworkResult.Success -> emit(
                MovieResult.Success(
                    Details(
                        title = result.data.originalTitle,
                        overview = result.data.overview,
                        backdropImagePath = backdropImagePathOrBlank(result.data.backdropImagePath),
                        posterImagePath = posterImageUrlOrBlank(result.data.posterImagePath),
                        releaseDate = dateFormatter.dateStringToPattern(result.data.releaseDate, DatePattern.yyyy),
                        budget = numberFormatter.usdCurrency(result.data.budget),
                        revenue = numberFormatter.usdCurrency(result.data.revenue),
                    ),
                )
            )

            is NetworkResult.Error -> emit(
                MovieResult.Error(result.exception.message)
            )
        }
    }.flowOn(ioDispatcher)

    override fun searchMovie(query: String): Flow<MovieResult<List<Movie>>> = flow {
        when (val result = dataSource.searchMovie(query)) {
            is NetworkResult.Success -> emit(
                MovieResult.Success(
                    result.data.results.map {
                        Movie(
                            id = it.id,
                            title = it.title,
                            overview = it.overview,
                            originalLanguage = originalLanguageOrBlank(it.originalLanguage),
                            posterImagePath = posterImageUrlOrBlank(it.posterPath)
                        )
                    }
                )
            )

            is NetworkResult.Error -> emit(
                MovieResult.Error(result.exception.message)
            )
        }
    }.flowOn(ioDispatcher)


    private fun posterImageUrlOrBlank(path: String?): String {
        return if (path != null) "${BuildConfig.IMAGE_BASE_URL}${POSTER_IMAGE_DIMENSIONS}$path" else EMPTY_STRING
    }

    private fun backdropImagePathOrBlank(path: String?): String {
       return if (path != null) "${BuildConfig.IMAGE_BASE_URL}${BACKDROP_IMAGE_DIMENSIONS}${path}" else EMPTY_STRING
    }

    private fun originalLanguageOrBlank(language: String?): String {
        return language ?: ""
    }


    companion object {
        private const val POSTER_IMAGE_DIMENSIONS = "/w600_and_h900_bestv2"
        private const val BACKDROP_IMAGE_DIMENSIONS = "/w1066_and_h600_bestv2"
        private const val EMPTY_STRING = ""
    }
}