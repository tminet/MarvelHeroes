package tmidev.marvelheroes.framework.paging

import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.core.domain.model.Character
import tmidev.marvelheroes.factory.response.CharacterPagingFactory
import tmidev.testing.model.CharactersFactory
import tmidev.testing.rule.MainCoroutineRule

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {
    private lateinit var charactersPagingSource: CharactersPagingSource

    @get:Rule
    var mainCoroutineRUle = MainCoroutineRule()

    @Mock
    private lateinit var remoteDataSource: RemoteCharactersDataSource

    private val dataWrapperResponseFactory = CharacterPagingFactory()
    private val charactersFactory = CharactersFactory()

    @Before
    fun setUp() {
        charactersPagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @Test
    fun `should return success load when getCharacters() is called`() = runBlockingTest {
        // arrange start
        whenever(
            remoteDataSource.getCharacters(queries = any())
        ).thenReturn(
            dataWrapperResponseFactory.create()
        )
        // arrange end

        // act start
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val charactersList = listOf(
            charactersFactory.create(CharactersFactory.Character.Character1),
            charactersFactory.create(CharactersFactory.Character.Character2)
        )
        // act end

        // assert start
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = charactersList,
                prevKey = null,
                nextKey = 20
            ),
            result
        )
        // assert end
    }

    @Test
    fun `should return an exception when getCharacters() is called`() = runBlockingTest {
        // arrange start
        val exception = RuntimeException()

        whenever(
            remoteDataSource.getCharacters(queries = any())
        ).thenThrow(
            exception
        )
        // arrange end

        // act start
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        // act end

        // assert start
        Assert.assertEquals(
            PagingSource.LoadResult.Error<Int, Character>(
                throwable = exception
            ),
            result
        )
        // assert end
    }
}