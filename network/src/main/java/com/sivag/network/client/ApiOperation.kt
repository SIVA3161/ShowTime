package com.sivag.network.client

sealed interface ApiOperation<T> {
    data class Success<T>(val data: T) : ApiOperation<T>
    data class Failure<T>(val exception: Exception) : ApiOperation<T>
    class Loading<Nothing> : ApiOperation<Nothing>

    fun <R> mapSuccess(transform: (T) -> R): ApiOperation<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Failure -> Failure(exception)
            is Loading -> Loading()
        }
    }

    fun onSuccess(block: (T) -> Unit): ApiOperation<T> {
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (Exception) -> Unit): ApiOperation<T> {
        if (this is Failure) block(exception)
        return this
    }

    fun onLoading(block: () -> Unit): ApiOperation<T> {
        if (this is Loading) block()
        return this
    }
}

inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
    return try {
        ApiOperation.Success(data = apiCall())
    } catch (e: Exception) {
        ApiOperation.Failure(exception = e)
    }
}