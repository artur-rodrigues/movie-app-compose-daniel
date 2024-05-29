package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.Poster
import br.com.movieapp.core.domain.model.PosterDetail
import br.com.movieapp.core.domain.model.createMovies
import br.com.movieapp.core.domain.model.toMovie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var useCase: GetMovieDetailUseCase

    @Mock
    lateinit var isFavoriteUseCase: IsMovieFavoriteUseCase

    @Mock
    lateinit var addMovieFavoriteUseCase: AddMovieFavoriteUseCase

    @Mock
    lateinit var deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetails = PosterDetail.Avengers.toMovie()
    private val movie = Poster.Avengers.toMovie()

    private val fakePagingDataMovies = PagingData.from(createMovies())

    private val viewModel by lazy {
        MovieDetailViewModel(
            useCase,
            isFavoriteUseCase,
            addMovieFavoriteUseCase,
            deleteMovieFavoriteUseCase,
            savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
            }
        )
    }

    @Test
    fun `must notify uiState with success when get movies similar and movie details returns success`() = runTest {
        // Given
        whenever(useCase.invoke(any(), any())).thenReturn(
            ResultData.Success(flowOf(fakePagingDataMovies) to movieDetails)
        )

        whenever(isFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(data = true))
        )

        val argumentCaptor = argumentCaptor<Int>()
        val pageConfigCaptor = argumentCaptor<PagingConfig>()

        // When
        viewModel.uiState.isLoading

        // Then
        verify(useCase).invoke(argumentCaptor.capture(), pageConfigCaptor.capture())
        assertThat(movieDetails.id).isEqualTo(argumentCaptor.firstValue)

        val movieDetails = viewModel.uiState.movieDetails
        val results = viewModel.uiState.result
        assertThat(movieDetails).isNotNull()
        assertThat(results).isNotNull()
    }

    @Test(expected = Exception::class)
    fun `must notify uiState with Failure when get movies details and returns exception`() = runTest {
        // Given
        val pageConfigCaptor = argumentCaptor<PagingConfig>()
        whenever(useCase.invoke(any(), any())).thenThrow(
            Exception("Um erro ocorreu!")
        )

        whenever(isFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(data = true))
        )

        val argumentCaptor = argumentCaptor<Int>()

        // When
        val error = viewModel.uiState.error

        // Then
        verify(useCase).invoke(argumentCaptor.capture(), pageConfigCaptor.capture())
        assertThat(error).isEqualTo("Um erro ocorreu!")
        assertThat(movieDetails).isNull()
    }

    @Test
    fun `must call delete favorite and notify of uiState with filled favorite icon when current icon is checked`() = runTest {
        // Given
        whenever(useCase.invoke(any(), any())).thenReturn(
            ResultData.Success(flowOf(fakePagingDataMovies) to movieDetails)
        )

        whenever(deleteMovieFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(Unit))
        )

        whenever(isFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(data = true))
        )

        val movieArgumentCaptor = argumentCaptor<Movie>()
        val intArgumentCaptor = argumentCaptor<Int>()

        // When
        viewModel.onAddFavorite(movie)

        // Then
        verify(deleteMovieFavoriteUseCase).invoke(movieArgumentCaptor.capture())
        assertThat(movie).isEqualTo(movieArgumentCaptor.firstValue)

        verify(isFavoriteUseCase).invoke(intArgumentCaptor.capture())
        assertThat(viewModel.uiState.iconColor).isEqualTo(Color.White)
    }

    @Test
    fun `must notify uiState with filled favorite icon when current icons is unchecked`() = runTest {
        // Given
        whenever(useCase.invoke(any(), any())).thenReturn(
            ResultData.Success(flowOf(fakePagingDataMovies) to movieDetails)
        )

        whenever(addMovieFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(Unit))
        )

        whenever(isFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(data = false))
        )

        val movieArgumentCaptor = argumentCaptor<Movie>()
        val intArgumentCaptor = argumentCaptor<Int>()

        // When
        viewModel.onAddFavorite(movie)

        // Then
        verify(addMovieFavoriteUseCase).invoke(movieArgumentCaptor.capture())
        assertThat(movie).isEqualTo(movieArgumentCaptor.firstValue)

        verify(isFavoriteUseCase).invoke(intArgumentCaptor.capture())
        assertThat(viewModel.uiState.iconColor).isEqualTo(Color.Red)
    }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark check returns true`() = runTest {
        // Given
        whenever(useCase.invoke(any(), any())).thenReturn(
            ResultData.Success(flowOf(fakePagingDataMovies) to movieDetails)
        )

        whenever(isFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(data = true))
        )

        val intArgumentCaptor = argumentCaptor<Int>()

        // When
        viewModel.uiState.isLoading

        // Then
        verify(isFavoriteUseCase).invoke(intArgumentCaptor.capture())
        assertThat(viewModel.uiState.iconColor).isEqualTo(Color.Red)
    }


    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark check returns false`() = runTest {
        // Given
        whenever(useCase.invoke(any(), any())).thenReturn(
            ResultData.Success(flowOf(fakePagingDataMovies) to movieDetails)
        )

        whenever(isFavoriteUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(data = false))
        )

        val intArgumentCaptor = argumentCaptor<Int>()

        // When
        viewModel.uiState.isLoading

        // Then
        verify(isFavoriteUseCase).invoke(intArgumentCaptor.capture())
        assertThat(viewModel.uiState.iconColor).isEqualTo(Color.White)
    }
}