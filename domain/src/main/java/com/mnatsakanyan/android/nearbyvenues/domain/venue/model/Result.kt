package com.mnatsakanyan.android.nearbyvenues.domain.venue.model

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GPSException
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.PermissionException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
    data class PermissionError(val exception: Throwable? = null) : Result<Nothing>
    data class GPSError(val exception: Throwable? = null) : Result<Nothing>
    object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
            .map<T, Result<T>> {
                Result.Success(it)
            }
            .onStart { emit(Result.Loading) }
            .catch { exception ->
                when (exception) {
                    is PermissionException -> {
                        emit(Result.PermissionError(exception))
                    }
                    is GPSException -> {
                        emit(Result.GPSError(exception))
                    }
                    else -> {
                        emit(Result.Error(exception))
                    }
                }
            }
}
