package com.freddominant.eurozahl.utils

import com.freddominant.eurozahl.core.utils.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestCoroutineDispatcherProvider : CoroutineDispatcherProvider() {
    override val io: CoroutineDispatcher = Dispatchers.Unconfined
}