package xyz.leomurca.lmdb.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkDetailsResponse(
    val results: List<NetworkDetails>,
)
