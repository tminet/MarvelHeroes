package tmidev.core.data.datasource

import tmidev.core.domain.model.CharacterPaging
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event

interface RemoteCharactersDataSource {
    suspend fun getCharacters(queries: Map<String, String>): CharacterPaging

    suspend fun getComics(characterId: Int): List<Comic>

    suspend fun getEvents(characterId: Int): List<Event>
}