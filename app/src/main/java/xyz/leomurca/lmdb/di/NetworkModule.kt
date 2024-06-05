package xyz.leomurca.lmdb.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import xyz.leomurca.lmdb.BuildConfig
import xyz.leomurca.lmdb.network.HeaderInterceptor
import xyz.leomurca.lmdb.network.NetworkDataSource
import xyz.leomurca.lmdb.network.remote.ApiService
import xyz.leomurca.lmdb.network.fake.FakeAssetManager
import xyz.leomurca.lmdb.network.remote.RemoteNetworkDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesFakeAssetManager(
        @ApplicationContext context: Context,
    ): FakeAssetManager = FakeAssetManager(context.assets::open)

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJsonConverterFactory(headerInterceptor: HeaderInterceptor): Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }

        return json.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, jsonConverterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(apiService: ApiService): NetworkDataSource {
        return RemoteNetworkDataSource(apiService)
    }
}
