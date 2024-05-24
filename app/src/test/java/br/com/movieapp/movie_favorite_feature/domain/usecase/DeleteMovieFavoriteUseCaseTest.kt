package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.Poster
import br.com.movieapp.core.domain.model.toMovie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth.assertThat
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
class DeleteMovieFavoriteUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = Poster.Avengers.toMovie()

    private val useCase by lazy {
        DeleteMovieFavoriteUseCase(movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository return success equal unit`() = runTest {
        // Given
        whenever(movieFavoriteRepository.delete(movie)).thenReturn(Unit)

        // When
        val result = useCase.invoke(movie).last()

        // Then
        assertThat(result).isEqualTo(ResultData.Success(Unit))

    }

    @Test
    fun `must return Failure from ResultStatus when the repository throw exception`() = runTest {
        // Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.delete(movie)).thenThrow(exception)

        // When
        val result = useCase.invoke(movie).last()

        // Then
        assertThat(result).isEqualTo(ResultData.Failure(exception))

    }
}