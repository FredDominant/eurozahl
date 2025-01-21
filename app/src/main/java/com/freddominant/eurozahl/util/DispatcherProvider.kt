package com.freddominant.eurozahl.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class CoroutineDispatcherProvider(
    override val io: CoroutineDispatcher = Dispatchers.IO
) : DispatcherProvider

interface DispatcherProvider {
    val io: CoroutineDispatcher
}