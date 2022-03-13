package tmidev.marvelheroes.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.marvelheroes.BuildConfig
import tmidev.marvelheroes.framework.di.qualifier.BaseUrl

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {
    @Provides
    @BaseUrl
    fun provideApiBaseUrl(): String = BuildConfig.BASE_URL
}