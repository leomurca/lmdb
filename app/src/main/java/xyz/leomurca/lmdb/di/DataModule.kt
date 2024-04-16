package xyz.leomurca.lmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.fake.FakeMovieRepository
import xyz.leomurca.lmdb.network.fake.FakeNetworkDataSource
import xyz.leomurca.lmdb.utils.DateFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        dataSource: FakeNetworkDataSource,
        dateFormatter: DateFormatter,
    ): MovieRepository {
        return FakeMovieRepository(ioDispatcher, dataSource, dateFormatter)
    }
}
