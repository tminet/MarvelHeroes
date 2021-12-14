package tmidev.core.data.datasource

interface RemoteCharactersDataSource<T> {
    suspend fun getCharacters(queries: Map<String, String>): T
}