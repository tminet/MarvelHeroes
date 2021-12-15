package tmidev.core.data.repository

interface CharactersRepository<T> {
    fun getCharacters(query: String): T
}