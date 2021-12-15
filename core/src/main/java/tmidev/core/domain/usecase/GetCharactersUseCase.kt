package tmidev.core.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tmidev.core.domain.model.Character

interface GetCharactersUseCase {
    operator fun invoke(
        params: GetCharactersParams
    ): Flow<PagingData<Character>>

    data class GetCharactersParams(
        val query: String,
        val pagingConfig: PagingConfig
    )
}