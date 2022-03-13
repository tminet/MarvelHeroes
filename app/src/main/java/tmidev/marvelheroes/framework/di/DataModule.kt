package tmidev.marvelheroes.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.core.data.repository.CharactersRepository
import tmidev.marvelheroes.framework.CharactersRepositoryImpl
import tmidev.marvelheroes.framework.remote.retrofit.RetrofitCharactersDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindRemoteCharactersDataSource(
        dataSource: RetrofitCharactersDataSource
    ): RemoteCharactersDataSource

    @Binds
    fun bindCharactersRepository(
        repository: CharactersRepositoryImpl
    ): CharactersRepository
}