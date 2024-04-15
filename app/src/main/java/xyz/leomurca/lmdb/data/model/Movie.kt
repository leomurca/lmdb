package xyz.leomurca.lmdb.data.model

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val originalLanguage: String,
    val posterImagePath: String,
)
