package tmidev.testing.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tmidev.core.domain.model.Character

class PagingSourceFactory {
    fun create(characters: List<Character>) = object : PagingSource<Int, Character>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
            return LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = 20
            )
        }

        override fun getRefreshKey(state: PagingState<Int, Character>): Int = 1
    }
}