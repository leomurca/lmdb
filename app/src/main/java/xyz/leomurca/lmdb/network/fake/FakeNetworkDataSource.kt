package xyz.leomurca.lmdb.network.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import xyz.leomurca.lmdb.JvmUnitTestFakeAssetManager
import xyz.leomurca.lmdb.di.AppDispatchers.IO
import xyz.leomurca.lmdb.di.Dispatcher
import xyz.leomurca.lmdb.network.NetworkDataSource
import xyz.leomurca.lmdb.network.NetworkResult
import xyz.leomurca.lmdb.network.model.NetworkDetails
import xyz.leomurca.lmdb.network.model.NetworkMovieResponse
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : NetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun popularMovies(): NetworkResult<NetworkMovieResponse> = TODO()

    override suspend fun details(id: Long): NetworkResult<NetworkDetails> = TODO()

    override suspend fun searchMovie(query: String): NetworkResult<NetworkMovieResponse> = TODO()

    companion object {
        private const val POPULAR_MOVIES_ASSET = "popular-movies.json"
        private const val DETAILS_ASSET = "movie-details.json"
    }
}
