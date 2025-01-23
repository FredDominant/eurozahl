package com.freddominant.eurozahl.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

open class CoroutineDispatcherProvider @Inject constructor () {
    open val io: CoroutineDispatcher = Dispatchers.IO
}
