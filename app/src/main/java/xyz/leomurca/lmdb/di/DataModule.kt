package xyz.leomurca.lmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.fake.FakeMovieRepository
import xyz.leomurca.lmdb.data.repository.remote.RemoteMovieRepository
import xyz.leomurca.lmdb.network.NetworkDataSource
import xyz.leomurca.lmdb.network.fake.FakeNetworkDataSource
import xyz.leomurca.lmdb.network.remote.ApiService
import xyz.leomurca.lmdb.network.remote.RemoteNetworkDataSource
import xyz.leomurca.lmdb.utils.DateFormatter
import xyz.leomurca.lmdb.utils.NumberFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        dataSource: NetworkDataSource,
        dateFormatter: DateFormatter,
        numberFormatter: NumberFormatter
    ): MovieRepository {
        return RemoteMovieRepository(ioDispatcher, dataSource, dateFormatter, numberFormatter)
    }
}
