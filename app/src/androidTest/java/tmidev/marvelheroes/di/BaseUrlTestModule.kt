package tmidev.marvelheroes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.marvelheroes.framework.di.qualifier.BaseUrl

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlTestModule {
    @Provides
    @BaseUrl
    fun provideApiBaseUrl(): String = "http://localhost:8080/"
}