package com.freddominant.eurozahl.model


sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val error: Exception) : Result<Nothing>
}