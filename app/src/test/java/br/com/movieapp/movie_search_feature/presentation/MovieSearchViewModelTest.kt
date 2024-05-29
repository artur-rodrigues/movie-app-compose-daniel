package br.com.movieapp.movie_search_feature.presentation

import androidx.paging.PagingData
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.createMovies
import br.com.movieapp.movie_search_feature.domain.usecase.GetMovieSearchUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
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
class MovieSearchViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieSearchUseCase: GetMovieSearchUseCase

    private val viewModel by lazy {
        MovieSearchViewModel(getMovieSearchUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(createMovies())

    @Test
    fun `must validate paging data object values when calling movie search paging data`() = runTest {
        // Given
        whenever(getMovieSearchUseCase.invoke(any(), any())).thenReturn(
            flowOf(fakePagingDataMovies)
        )

        // When
        viewModel.fetch("")
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to use case returns an exception`() = runTest {
        // Given
        whenever(getMovieSearchUseCase.invoke(any(), any())).thenThrow(
            RuntimeException()
        )

        // When
        viewModel.fetch("")
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNull()
    }
}