package xyz.leomurca.lmdb.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkDetails(
    val id: Long,
    val budget: Float,
    val revenue: Float,
    val overview: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("backdrop_path") val backdropImagePath: String,
    @SerialName("release_date") val releaseDate: String,
)
