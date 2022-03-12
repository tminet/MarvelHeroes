package tmidev.core.domain.usecase

import androidx.paging.PagingConfig
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import tmidev.core.data.repository.CharactersRepository
import tmidev.testing.model.CharactersFactory
import tmidev.testing.paging.PagingSourceFactory
import tmidev.testing.rule.MainCoroutineRule

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @get:Rule
    var mainCoroutineRUle = MainCoroutineRule()

    @Mock
    private lateinit var repository: CharactersRepository

    private val character = CharactersFactory().create(CharactersFactory.Character.Character1)

    private val fakePagingSource = PagingSourceFactory().create(listOf(character))

    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @Test
    fun `should return a flow paging data with a list of characters`() = runBlockingTest {
        // arrange start
        whenever(
            repository.getCharacters(query = "")
        ).thenReturn(
            fakePagingSource
        )
        // arrange end

        // act start
        val result = getCharactersUseCase.invoke(
            GetCharactersUseCase.GetCharactersParams(
                query = "",
                pagingConfig = PagingConfig(pageSize = 20)
            )
        )
        // act end

        // assert start
        verify(repository).getCharacters(query = "")

        TestCase.assertNotNull(result.first())
        // assert end
    }
}