package br.com.movieapp.movie_popular_feature.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviePopularRepository
) {

    fun invoke(pagingConfig: PagingConfig):Flow<PagingData<Movie>> {
        return try {
            Pager(
                config = pagingConfig,
                pagingSourceFactory = {
                    repository.getPopularMovies()
                }
            ).flow
        } catch (e: Exception) {
            emptyFlow()
        }
    }
}