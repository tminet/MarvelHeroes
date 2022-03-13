package tmidev.core.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface CoroutinesDispatchers {
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

class AppCoroutinesDispatchers @Inject constructor() : CoroutinesDispatchers