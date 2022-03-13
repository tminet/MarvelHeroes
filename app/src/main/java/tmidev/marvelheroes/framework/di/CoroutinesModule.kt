package tmidev.marvelheroes.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.core.domain.usecase.base.AppCoroutinesDispatchers
import tmidev.core.domain.usecase.base.CoroutinesDispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {
    @Binds
    @Singleton
    fun bindDispatchers(
        dispatchers: AppCoroutinesDispatchers
    ): CoroutinesDispatchers
}