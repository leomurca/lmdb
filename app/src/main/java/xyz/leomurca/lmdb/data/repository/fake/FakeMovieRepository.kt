package xyz.leomurca.lmdb.data.repository.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import xyz.leomurca.lmdb.BuildConfig
import xyz.leomurca.lmdb.data.model.Details
import xyz.leomurca.lmdb.data.model.Movie
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.MovieResult
import xyz.leomurca.lmdb.di.AppDispatchers.IO
import xyz.leomurca.lmdb.di.Dispatcher
import xyz.leomurca.lmdb.network.fake.FakeNetworkDataSource
import xyz.leomurca.lmdb.utils.DateFormatter
import xyz.leomurca.lmdb.utils.DatePattern
import xyz.leomurca.lmdb.utils.NumberFormatter
import javax.inject.Inject

class FakeMovieRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: FakeNetworkDataSource,
    private val dateFormatter: DateFormatter,
    private val numberFormatter: NumberFormatter,
) : MovieRepository {
    override fun popularMovies(): Flow<MovieResult<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun details(id: Long): Flow<MovieResult<Details>> {
        TODO("Not yet implemented")
    }

    override fun searchMovie(query: String): Flow<MovieResult<List<Movie>>> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val POSTER_IMAGE_DIMENSIONS = "/w600_and_h900_bestv2"
        private const val BACKDROP_IMAGE_DIMENSIONS = "/w1066_and_h600_bestv2"
    }
}
