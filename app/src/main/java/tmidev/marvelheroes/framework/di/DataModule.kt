package tmidev.marvelheroes.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.marvelheroes.framework.remote.response.DataWrapperResponse
import tmidev.marvelheroes.framework.remote.retrofit.RetrofitCharactersDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindRemoteCharactersDataSource(
        datasource: RetrofitCharactersDataSource
    ): RemoteCharactersDataSource<DataWrapperResponse>
}