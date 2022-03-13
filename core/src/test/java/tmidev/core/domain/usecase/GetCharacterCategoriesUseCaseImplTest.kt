package tmidev.core.domain.usecase

import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import tmidev.core.data.repository.CharactersRepository
import tmidev.core.domain.usecase.base.ResultStatus
import tmidev.testing.model.CharactersFactory
import tmidev.testing.model.ComicFactory
import tmidev.testing.model.EventFactory
import tmidev.testing.rule.MainCoroutineRule

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterCategoriesUseCaseImplTest {
    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CharactersRepository

    private val character =
        CharactersFactory().create(CharactersFactory.FakeCharacter.FakeCharacter1)

    private val comics = listOf(
        ComicFactory().create(ComicFactory.FakeComic.FakeComic1),
        ComicFactory().create(ComicFactory.FakeComic.FakeComic2)
    )

    private val events = listOf(
        EventFactory().create(EventFactory.FakeEvent.FakeEvent1),
        EventFactory().create(EventFactory.FakeEvent.FakeEvent2)
    )

    @Before
    fun setUp() {
        getCharacterCategoriesUseCase =
            GetCharacterCategoriesUseCaseImpl(repository, mainCoroutineRule.testDispatcherProvider)
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() =
        runTest {
            // ------------------ Arrange ------------------
            whenever(
                repository.getComics(character.id)
            ).thenReturn(comics)

            whenever(
                repository.getEvents(character.id)
            ).thenReturn(events)

            // ------------------ Act ------------------
            val result = getCharacterCategoriesUseCase(
                GetCharacterCategoriesUseCase.GetCategoriesParams(character.id)
            )

            // ------------------ Assert ------------------
            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Success)
        }

    @Test
    fun `should return Error from ResultStatus when get comics request returns error`() =
        runTest {
            // ------------------ Arrange ------------------
            whenever(
                repository.getComics(character.id)
            ).thenAnswer {
                throw Throwable()
            }

            // ------------------ Act ------------------
            val result = getCharacterCategoriesUseCase(
                GetCharacterCategoriesUseCase.GetCategoriesParams(character.id)
            )

            // ------------------ Assert ------------------
            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Error)
        }

    @Test
    fun `should return Error from ResultStatus when get events request returns error`() =
        runTest {
            // ------------------ Arrange ------------------
            whenever(
                repository.getComics(character.id)
            ).thenReturn(comics)

            whenever(
                repository.getEvents(character.id)
            ).thenAnswer {
                throw Throwable()
            }

            // ------------------ Act ------------------
            val result = getCharacterCategoriesUseCase(
                GetCharacterCategoriesUseCase.GetCategoriesParams(character.id)
            )

            // ------------------ Assert ------------------
            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Error)
        }
}