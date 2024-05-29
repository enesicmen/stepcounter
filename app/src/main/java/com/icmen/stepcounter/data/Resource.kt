package com.icmen.stepcounter.data

sealed class Resource<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
