package tmidev.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event
import tmidev.core.domain.usecase.base.ResultStatus

interface GetCharacterCategoriesUseCase {
    operator fun invoke(params: GetCharacterCategoriesParams):
            Flow<ResultStatus<Pair<List<Comic>, List<Event>>>>

    data class GetCharacterCategoriesParams(
        val characterId: Int,
    )
}