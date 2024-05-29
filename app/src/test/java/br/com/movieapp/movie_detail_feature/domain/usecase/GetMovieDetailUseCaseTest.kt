package br.com.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.Poster
import br.com.movieapp.core.domain.model.PosterDetail
import br.com.movieapp.core.domain.model.createMoviePagingSource
import br.com.movieapp.core.domain.model.toMovie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieDetailRepository: MovieDetailRepository

    private val movie = Poster.Avengers.toMovie()

    private val movieDetail = PosterDetail.Avengers.toMovie()

    private val pagingSourceFake = createMoviePagingSource(listOf(movie))

    private val getMovieDetailUseCase by lazy {
        GetMovieDetailUseCase(movieDetailRepository)
    }

    @Test
    fun `should returns Success from ResultStatus when get both request return success`() = runTest {
        // Given
        whenever(movieDetailRepository.getMovieDetails(movie.id)).thenReturn(movieDetail)

        whenever(movieDetailRepository.getMovieSimilar(movie.id)).thenReturn(pagingSourceFake)

        // When
        val result = getMovieDetailUseCase.invoke(movie.id, PagingConfig(20, initialLoadSize = 20))

        // Then
        verify(movieDetailRepository).getMovieDetails(movie.id)
        verify(movieDetailRepository).getMovieSimilar(movie.id)

        assertThat(result).isNotNull()
        assertThat(result is ResultData.Success).isTrue()
    }

    @Test
    fun `should returns Error from ResultStatus when get movieDetails request returns error`() = runTest {
        // Given
        val exception = RuntimeException()
        whenever(movieDetailRepository.getMovieDetails(movie.id)).thenThrow(exception)

        whenever(movieDetailRepository.getMovieSimilar(movie.id)).thenReturn(pagingSourceFake)

        // When
        val result = getMovieDetailUseCase.invoke(movie.id, PagingConfig(20, initialLoadSize = 20))

        // Then
        verify(movieDetailRepository).getMovieDetails(movie.id)

        assertThat(result).isNotNull()
        assertThat(result is ResultData.Failure).isTrue()
    }

    @Test
    fun `should returns Error from ResultStatus when get movieSimilar request returns error`() = runTest {
        // Given
        val exception = RuntimeException()
        whenever(movieDetailRepository.getMovieSimilar(movie.id)).thenThrow(exception)

        // When
        val result = getMovieDetailUseCase.invoke(movie.id, PagingConfig(20, initialLoadSize = 20))

        // Then
        verify(movieDetailRepository).getMovieSimilar(movie.id)

        assertThat(result).isNotNull()
        assertThat(result is ResultData.Failure).isTrue()
    }

}