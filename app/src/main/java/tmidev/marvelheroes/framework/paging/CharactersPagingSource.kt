package tmidev.marvelheroes.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.core.domain.model.Character
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val remoteDataSource: RemoteCharactersDataSource,
    private val query: String
) : PagingSource<Int, Character>() {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val offset = params.key ?: 0
            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            if (query.isNotEmpty()) queries["nameStartWith"] = query

            val characterPaging = remoteDataSource.getCharacters(queries)
            val offsetCharacters = characterPaging.offset
            val totalCharacters = characterPaging.total

            LoadResult.Page(
                data = characterPaging.characters,
                prevKey = null,
                nextKey = if (offsetCharacters < totalCharacters) {
                    offsetCharacters + LIMIT
                } else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}