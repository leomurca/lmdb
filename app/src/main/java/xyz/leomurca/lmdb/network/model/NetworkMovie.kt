package xyz.leomurca.lmdb.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovie(
    val title: String,
    val overview: String,
    @SerialName("original_language") val originalLanguage: String,
)
