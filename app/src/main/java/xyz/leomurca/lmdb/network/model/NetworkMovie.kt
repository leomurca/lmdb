package xyz.leomurca.lmdb.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovie(
    val id: Long,
    val title: String,
    val overview: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("poster_path") val posterPath: String,
)
