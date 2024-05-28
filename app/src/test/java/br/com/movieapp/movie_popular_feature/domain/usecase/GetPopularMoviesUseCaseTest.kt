package br.com.movieapp.movie_popular_feature.domain.usecase

import androidx.paging.PagingConfig
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.Poster
import br.com.movieapp.core.domain.model.createMoviePagingSource
import br.com.movieapp.core.domain.model.toMovie
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.google.common.truth.Truth.assertThat
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
class GetPopularMoviesUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()


    @Mock
    lateinit var repository: MoviePopularRepository

    private val movie = Poster.Avengers.toMovie()

    private val pagingSourceFake = createMoviePagingSource(listOf(movie))

    private val useCase by lazy {
        GetPopularMoviesUseCase(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        // Given
        whenever(repository.getPopularMovies()).thenReturn(pagingSourceFake)

        // When
        val result = useCase.invoke(PagingConfig(20, initialLoadSize = 20)).first()

        // Then
        verify(repository).getPopularMovies()
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() = runTest {
        // Given
        val exception = RuntimeException()
        whenever(repository.getPopularMovies()).thenThrow(exception)

        // When
        val result = useCase.invoke(PagingConfig(20, initialLoadSize = 20))

        // Then
        val resultList = result.toList()
        verify(repository).getPopularMovies()
        assertThat(resultList).isEmpty()
    }

}