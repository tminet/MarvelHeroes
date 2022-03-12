package tmidev.marvelheroes.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import tmidev.core.domain.usecase.base.AppCoroutinesDispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {
    @Provides
    @Singleton
    fun provideDispatchers() = AppCoroutinesDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        computation = Dispatchers.Default
    )
}