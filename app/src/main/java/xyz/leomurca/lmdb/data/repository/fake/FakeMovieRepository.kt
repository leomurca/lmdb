package xyz.leomurca.lmdb.data.repository.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
    override fun getPopularMovies(): Flow<List<Movie>> = flow {
        emit(
            dataSource.getPopularMovies().results.map {
                Movie(
                    title = it.title,
                    overview = it.overview,
                    originalLanguage = it.originalLanguage,
                )
            },
        )
    }.flowOn(ioDispatcher)
}
