package com.example.sandbox_compose.utils

sealed class Resource<out T>(
       val data: T? = null,
       val error: Throwable? = null
) {

    class Loading<T> : Resource<T>()
    data class Success<out T>(val dataSuccess: T) : Resource<T>(data = dataSuccess)
    data class Error<T>(val throwable: Throwable?) : Resource<T>(error = throwable)
}
