package xyz.leomurca.lmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.leomurca.lmdb.utils.DateFormatter
import xyz.leomurca.lmdb.utils.SystemDateFormatter

@Module
@InstallIn(SingletonComponent::class)
class DateTimeModule {

    @Provides
    fun providesDateFormatter(): DateFormatter = SystemDateFormatter()
}
