package tmidev.marvelheroes.framework

import androidx.paging.PagingSource
import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.core.data.repository.CharactersRepository
import tmidev.core.domain.model.Character
import tmidev.marvelheroes.framework.paging.CharactersPagingSource
import tmidev.marvelheroes.framework.remote.response.DataWrapperResponse
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteCharactersDataSource<DataWrapperResponse>
) : CharactersRepository<PagingSource<Int, Character>> {
    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }
}