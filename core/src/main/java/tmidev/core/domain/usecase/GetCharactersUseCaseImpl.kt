package tmidev.core.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import tmidev.core.data.repository.CharactersRepository
import tmidev.core.domain.model.Character
import tmidev.core.domain.usecase.base.UseCasePaging
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository<PagingSource<Int, Character>>
) : UseCasePaging<GetCharactersUseCase.GetCharactersParams, Character>(),
    GetCharactersUseCase {
    override fun createFlow(params: GetCharactersUseCase.GetCharactersParams): Flow<PagingData<Character>> {
        val pagingSource = charactersRepository.getCharacters(params.query)
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }
}