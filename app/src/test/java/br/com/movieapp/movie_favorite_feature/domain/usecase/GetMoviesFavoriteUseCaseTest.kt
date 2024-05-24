package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.createMovies
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMoviesFavoriteUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movies = createMovies()

    private val useCase by lazy {
        GetMoviesFavoriteUseCase(movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository return success equal unit`() = runTest {
        // Given
        whenever(movieFavoriteRepository.getMovies()).thenReturn(flowOf(movies))

        // When
        val result = useCase.invoke().last()

        // Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(movies[1])
        assertThat(result).isEqualTo(movies)

    }

    @Test(expected = RuntimeException::class)
    fun `must return Failure from ResultStatus when the repository throw exception`() = runTest {
        // Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.getMovies()).thenThrow(exception)

        // When
        useCase.invoke().last()

        // Then
        // No assertion needed

    }
}