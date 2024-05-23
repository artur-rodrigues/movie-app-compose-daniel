package br.com.movieapp.movie_favorite_feature.presentation

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.createMovies
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMoviesFavoriteUseCase)
    }

    @Test
    fun `must validate the data object values when calling list of favorites`() = runTest {
        // Given
        whenever(getMoviesFavoriteUseCase.invoke()).thenReturn(
            flowOf(createMovies())
        )

        // When
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(createMovies()[0])
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to use case returns an exception`() = runTest {
        // Given
        whenever(getMoviesFavoriteUseCase.invoke()).thenThrow(
            RuntimeException()
        )

        // When
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNull()
    }

}