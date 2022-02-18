package tmidev.marvelheroes.presentation.home

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import tmidev.core.domain.usecase.GetCharactersUseCase
import tmidev.testing.model.CharactersFactory
import tmidev.testing.rule.MainCoroutineRule

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var mainCoroutineRUle = MainCoroutineRule()

    @Mock
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private val charactersFactory = CharactersFactory()

    private val charactersPagingData = PagingData.from(
        data = listOf(
            charactersFactory.create(CharactersFactory.Character.Character1),
            charactersFactory.create(CharactersFactory.Character.Character2)
        )
    )

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(getCharactersUseCase = getCharactersUseCase)
    }

    @Test
    fun `should return one paging data with characters`() = runTest {
        whenever(
            getCharactersUseCase.invoke(params = any())
        ).thenReturn(
            flowOf(charactersPagingData)
        )

        val result = homeViewModel.charactersPagingData(query = "")

        assertNotNull(result.first())
    }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when use case returns an exception`() = runTest {
        whenever(
            getCharactersUseCase.invoke(params = any())
        ).thenThrow(
            RuntimeException()
        )

        homeViewModel.charactersPagingData(query = "")
    }
}