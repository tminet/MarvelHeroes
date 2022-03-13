package tmidev.marvelheroes.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event
import tmidev.core.domain.usecase.GetCharacterCategoriesUseCase
import tmidev.core.domain.usecase.base.ResultStatus
import tmidev.marvelheroes.R
import tmidev.testing.model.CharactersFactory
import tmidev.testing.model.ComicFactory
import tmidev.testing.model.EventFactory
import tmidev.testing.rule.MainCoroutineRule

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var mainCoroutineRUle = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase

    @Mock
    private lateinit var uiStateObserver: Observer<DetailViewModel.UiState>

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
        detailViewModel = DetailViewModel(getCharacterCategoriesUseCase)
        detailViewModel.uiState.observeForever(uiStateObserver)
    }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns comics and events`() =
        runTest {
            // ------------------ Arrange ------------------
            val pair = comics to events

            whenever(getCharacterCategoriesUseCase(any()))
                .thenReturn(
                    flowOf(ResultStatus.Success(data = pair))
                )

            // ------------------ Act ------------------
            detailViewModel.getCharacterCategories(character.id)

            // ------------------ Assert ------------------
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as DetailViewModel.UiState.Success
            val detailsParentList = uiStateSuccess.detailParentList

            assertEquals(2, detailsParentList.size)
            assertEquals(
                R.string.detailsCategoryComics,
                detailsParentList[0].categoryStringRes
            )
            assertEquals(
                R.string.detailsCategoryEvents,
                detailsParentList[1].categoryStringRes
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only comics`() =
        runTest {
            // ------------------ Arrange ------------------
            val pair = comics to emptyList<Event>()

            whenever(getCharacterCategoriesUseCase(any()))
                .thenReturn(
                    flowOf(ResultStatus.Success(data = pair))
                )

            // ------------------ Act ------------------
            detailViewModel.getCharacterCategories(character.id)

            // ------------------ Assert ------------------
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as DetailViewModel.UiState.Success
            val detailsParentList = uiStateSuccess.detailParentList

            assertEquals(1, detailsParentList.size)
            assertEquals(
                R.string.detailsCategoryComics,
                detailsParentList[0].categoryStringRes
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only events`() =
        runTest {
            // ------------------ Arrange ------------------
            val pair = emptyList<Comic>() to events

            whenever(getCharacterCategoriesUseCase(any()))
                .thenReturn(
                    flowOf(ResultStatus.Success(data = pair))
                )

            // ------------------ Act ------------------
            detailViewModel.getCharacterCategories(character.id)

            // ------------------ Assert ------------------
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as DetailViewModel.UiState.Success
            val detailsParentList = uiStateSuccess.detailParentList

            assertEquals(1, detailsParentList.size)
            assertEquals(
                R.string.detailsCategoryEvents,
                detailsParentList[0].categoryStringRes
            )
        }

    @Test
    fun `should notify uiState with Empty from UiState when get character categories returns an empty result list`() =
        runTest {
            // ------------------ Arrange ------------------
            val pair = emptyList<Comic>() to emptyList<Event>()

            whenever(getCharacterCategoriesUseCase(any()))
                .thenReturn(
                    flowOf(ResultStatus.Success(data = pair))
                )

            // ------------------ Act ------------------
            detailViewModel.getCharacterCategories(character.id)

            // ------------------ Assert ------------------
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Empty>())
        }

    @Test
    fun `should notify uiState with Error from UiState when get character categories returns an exception`() =
        runTest {
            // ------------------ Arrange ------------------
            whenever(getCharacterCategoriesUseCase(any()))
                .thenReturn(
                    flowOf(ResultStatus.Error(throwable = Throwable()))
                )

            // ------------------ Act ------------------
            detailViewModel.getCharacterCategories(character.id)

            // ------------------ Assert ------------------
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Error>())
        }
}