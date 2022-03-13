package tmidev.marvelheroes.framework.remote.retrofit

import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.core.domain.model.CharacterPaging
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : RemoteCharactersDataSource {
    override suspend fun getCharacters(queries: Map<String, String>): CharacterPaging {
        val data = marvelApi.getCharacters(queries = queries).data
        val characters = data.results.map {
            it.toCharacterModel()
        }
        return CharacterPaging(
            data.offset,
            data.total,
            characters
        )
    }

    override suspend fun getComics(characterId: Int): List<Comic> {
        return marvelApi.getComics(characterId).data.results.map {
            it.toComicModel()
        }
    }

    override suspend fun getEvents(characterId: Int): List<Event> {
        return marvelApi.getEvents(characterId).data.results.map {
            it.toEventModel()
        }
    }
}