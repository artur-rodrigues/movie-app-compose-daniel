package br.com.movieapp.movie_search_feature.domain.usecase

import androidx.paging.PagingConfig
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.Poster
import br.com.movieapp.core.domain.model.createMoviePagingSource
import br.com.movieapp.core.domain.model.toMovie
import br.com.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieSearchUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: MovieSearchRepository

    private val pagingSource = createMoviePagingSource(listOf(Poster.Avengers.toMovie()))

    private val useCase by lazy {
        GetMovieSearchUseCase(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        // Given
        whenever(repository.getSearchMovies("")).thenReturn(pagingSource)

        // When
        val result = useCase.invoke("", PagingConfig(20, initialLoadSize = 20)).first()

        // Then
        verify(repository).getSearchMovies("")
        Truth.assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() = runTest {
        // Given
        val exception = RuntimeException()
        whenever(repository.getSearchMovies("")).thenThrow(exception)

        // When
        val result = useCase.invoke("", PagingConfig(20, initialLoadSize = 20))

        // Then
        val resultList = result.toList()
        verify(repository).getSearchMovies("")
        Truth.assertThat(resultList).isEmpty()
    }
}