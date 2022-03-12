package tmidev.core.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher

//interface CoroutinesDispatchers {
//    fun main(): CoroutineDispatcher = Dispatchers.Main
//    fun default(): CoroutineDispatcher = Dispatchers.Default
//    fun io(): CoroutineDispatcher = Dispatchers.IO
//    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
//}

//class AppCoroutinesDispatchers @Inject constructor() : CoroutinesDispatchers

data class AppCoroutinesDispatchers(
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher
)