package tmidev.marvelheroes.framework.di

import androidx.paging.PagingSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.core.data.repository.CharactersRepository
import tmidev.core.domain.model.Character
import tmidev.marvelheroes.framework.CharactersRepositoryImpl
import tmidev.marvelheroes.framework.remote.response.DataWrapperResponse
import tmidev.marvelheroes.framework.remote.retrofit.RetrofitCharactersDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindRemoteCharactersDataSource(
        dataSource: RetrofitCharactersDataSource
    ): RemoteCharactersDataSource<DataWrapperResponse>

    @Binds
    fun bindCharactersRepository(
        repository: CharactersRepositoryImpl
    ): CharactersRepository<PagingSource<Int, Character>>
}