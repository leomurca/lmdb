package xyz.leomurca.lmdb.data.repository.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import xyz.leomurca.lmdb.BuildConfig
import xyz.leomurca.lmdb.data.model.Details
import xyz.leomurca.lmdb.data.model.Movie
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.di.AppDispatchers.IO
import xyz.leomurca.lmdb.di.Dispatcher
import xyz.leomurca.lmdb.network.fake.FakeNetworkDataSource
import javax.inject.Inject

class FakeMovieRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: FakeNetworkDataSource,
) : MovieRepository {
    override fun popularMovies(): Flow<List<Movie>> = flow {
        emit(
            dataSource.popularMovies().results.map {
                Movie(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    originalLanguage = it.originalLanguage,
                    posterImagePath = "${BuildConfig.IMAGE_BASE_URL}$POSTER_IMAGE_DIMENSIONS${it.posterPath}",
                )
            },
        )
    }.flowOn(ioDispatcher)

    override fun details(id: Long): Flow<Details> = flow {
        val details = dataSource.details(id)
        emit(
            Details(
                title = details.originalTitle,
                overview = details.overview,
                backdropImagePath = "${BuildConfig.IMAGE_BASE_URL}$BACKDROP_IMAGE_DIMENSIONS${details.backdropImagePath}",
                releaseDate = details.releaseDate,
                budget = details.budget,
                revenue = details.revenue,
            ),
        )
    }

    companion object {
        private const val POSTER_IMAGE_DIMENSIONS = "/w600_and_h900_bestv2"
        private const val BACKDROP_IMAGE_DIMENSIONS = "/w1066_and_h600_bestv2"
    }
}
