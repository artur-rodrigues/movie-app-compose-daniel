package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.Poster
import br.com.movieapp.core.domain.model.toMovie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class IsMovieFavoriteUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = Poster.Avengers.toMovie()

    private val useCase by lazy {
        IsMovieFavoriteUseCase(movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository return success equal true`() = runTest {
        // Given
        whenever(movieFavoriteRepository.isFavorite(any())).thenReturn(true)

        // When
        val result = useCase.invoke(movie.id).last()

        // Then
        assertThat(result).isEqualTo(ResultData.Success(true))

    }

    @Test
    fun `must return Success from ResultStatus when the repository return success equal false`() = runTest {
        // Given
        whenever(movieFavoriteRepository.isFavorite(any())).thenReturn(false)

        // When
        val result = useCase.invoke(movie.id).last()

        // Then
        assertThat(result).isEqualTo(ResultData.Success(false))

    }

    @Test
    fun `must return Failure from ResultStatus when the repository throw exception`() = runTest {
        // Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.isFavorite(any())).thenThrow(exception)

        // When
        val result = useCase.invoke(movie.id).last()

        // Then
        assertThat(result).isEqualTo(ResultData.Failure(exception))
    }
}