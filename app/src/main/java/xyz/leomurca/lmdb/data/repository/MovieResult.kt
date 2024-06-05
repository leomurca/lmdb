package xyz.leomurca.lmdb.data.repository

sealed class MovieResult<out T> {
    data class Success<T>(val data: T) : MovieResult<T>()
    data class Error(val message: String) : MovieResult<Nothing>()
}