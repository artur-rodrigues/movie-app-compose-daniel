package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieSearchPagingFactory
import br.com.movieapp.core.domain.model.createMovies
import br.com.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
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
class MovieSearchPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieSearchRemoteDataSource

    private val movieSearchPaging = MovieSearchPagingFactory.create()

    private val moviePagingSource by lazy {
        MovieSearchPagingSource("", remoteDataSource)
    }
    
    @Test
    fun `must return a success load result when load is called`() = runTest {
        // Given
        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenReturn(movieSearchPaging)
        
        // When
        val result = moviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = createMovies()
        
        // Then
        assertThat(resultExpected).isEqualTo(
            (result as PagingSource.LoadResult.Page<Int, Movie>).data)
    }

    @Test
    fun `must return a error load result when load is called`() = runTest {
        val exception = RuntimeException()
        // Given
        whenever(remoteDataSource.getSearchMovies(any(), any())).thenThrow(exception)

        // When
        val result = moviePagingSource.load(
            PagingSource.LoadParams.Refresh(null, 2, false)
        )

        // Then
        assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception))
            .isEqualTo(result)
    }
}