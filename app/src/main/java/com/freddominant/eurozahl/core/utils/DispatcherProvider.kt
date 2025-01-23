package com.freddominant.eurozahl.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatcherProvider @Inject constructor () {
    val io: CoroutineDispatcher = Dispatchers.IO
}
