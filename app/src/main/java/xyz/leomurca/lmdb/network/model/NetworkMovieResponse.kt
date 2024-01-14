package xyz.leomurca.lmdb.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovieResponse(
    @SerialName("page") val currentPage: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
    val results: List<NetworkMovie>,
)
