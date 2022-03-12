package tmidev.core.domain.usecase

import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import tmidev.core.data.repository.CharactersRepository
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event
import tmidev.core.domain.usecase.base.AppCoroutinesDispatchers
import tmidev.core.domain.usecase.base.ResultStatus
import tmidev.core.domain.usecase.base.UseCase
import javax.inject.Inject

class GetCharacterCategoriesUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository,
    private val dispatchers: AppCoroutinesDispatchers
) : GetCharacterCategoriesUseCase,
    UseCase<GetCharacterCategoriesUseCase.GetCharacterCategoriesParams,
            Pair<List<Comic>, List<Event>>>() {
    override suspend fun doWork(
        params: GetCharacterCategoriesUseCase.GetCharacterCategoriesParams
    ): ResultStatus<Pair<List<Comic>, List<Event>>> {
        return withContext(dispatchers.io) {
            val comicsDeferred = async { repository.getComics(params.characterId) }
            val eventsDeferred = async { repository.getEvents(params.characterId) }

            val comics = comicsDeferred.await()
            val events = eventsDeferred.await()

            ResultStatus.Success(data = comics to events)
        }
    }
}