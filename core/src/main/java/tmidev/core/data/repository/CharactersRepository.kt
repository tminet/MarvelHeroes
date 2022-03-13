package tmidev.core.data.repository

import androidx.paging.PagingSource
import tmidev.core.domain.model.Character
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>

    suspend fun getComics(characterId: Int): List<Comic>

    suspend fun getEvents(characterId: Int): List<Event>
}