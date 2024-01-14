package xyz.leomurca.lmdb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.leomurca.lmdb.data.repository.MovieRepository
import xyz.leomurca.lmdb.data.repository.fake.FakeMovieRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsMovieRepository(
        fakeTopicsRepository: FakeMovieRepository,
    ): MovieRepository
}
