package xyz.leomurca.lmdb.data.model

data class Details(
    val title: String,
    val overview: String,
    val backdropImagePath: String,
    val posterImagePath: String,
    val releaseDate: String,
    val budget: Float,
    val revenue: Float,
)
