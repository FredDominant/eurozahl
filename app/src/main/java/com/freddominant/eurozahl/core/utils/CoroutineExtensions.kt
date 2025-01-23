package com.freddominant.eurozahl.core.utils

import com.freddominant.eurozahl.domain.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> CoroutineContext.executeTask(task: suspend (scope: CoroutineScope) -> T): Result<T> {
    val coroutineContext = this
    return try {
        val data = withContext(coroutineContext) {
            task(this)
        }
        Result.Success(data)
    } catch (exception: Exception) {
        Result.Error(exception)
    }
}
