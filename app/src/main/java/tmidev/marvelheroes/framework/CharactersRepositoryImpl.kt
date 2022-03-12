package tmidev.marvelheroes.framework

import androidx.paging.PagingSource
import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.core.data.repository.CharactersRepository
import tmidev.core.domain.model.Character
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event
import tmidev.marvelheroes.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteCharactersDataSource
) : CharactersRepository {
    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

    override suspend fun getComics(characterId: Int): List<Comic> {
        return remoteDataSource.getComics(characterId)
    }

    override suspend fun getEvents(characterId: Int): List<Event> {
        return remoteDataSource.getEvents(characterId)
    }
}