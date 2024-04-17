package xyz.leomurca.lmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.leomurca.lmdb.utils.DateFormatter
import xyz.leomurca.lmdb.utils.NumberFormatter
import xyz.leomurca.lmdb.utils.SystemDateFormatter
import xyz.leomurca.lmdb.utils.SystemNumberFormatter

@Module
@InstallIn(SingletonComponent::class)
class FormattersModule {

    @Provides
    fun providesDateFormatter(): DateFormatter = SystemDateFormatter()

    @Provides
    fun providesNumberFormatter(): NumberFormatter = SystemNumberFormatter()
}
