package xyz.leomurca.lmdb.network.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import xyz.leomurca.lmdb.JvmUnitTestFakeAssetManager
import xyz.leomurca.lmdb.di.AppDispatchers.IO
import xyz.leomurca.lmdb.di.Dispatcher
import xyz.leomurca.lmdb.network.NetworkDataSource
import xyz.leomurca.lmdb.network.model.NetworkDetails
import xyz.leomurca.lmdb.network.model.NetworkDetailsResponse
import xyz.leomurca.lmdb.network.model.NetworkMovieResponse
import java.io.InputStream
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : NetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun popularMovies(): NetworkMovieResponse =
        withContext(ioDispatcher) {
            assets.open(POPULAR_MOVIES_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun details(id: Long): NetworkDetails =
        withContext(ioDispatcher) {
            assets.open(DETAILS_ASSET).use<InputStream, NetworkDetailsResponse>(networkJson::decodeFromStream).firstBy(id)!!
        }

    private fun NetworkDetailsResponse.firstBy(id: Long): NetworkDetails? {
        return results.firstOrNull { it.id == id }
    }

    companion object {
        private const val POPULAR_MOVIES_ASSET = "popular-movies.json"
        private const val DETAILS_ASSET = "movie-details.json"
    }
}
