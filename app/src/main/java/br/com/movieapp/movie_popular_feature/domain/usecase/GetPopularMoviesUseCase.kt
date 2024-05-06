package br.com.movieapp.movie_popular_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviePopularRepository
) {

    fun invoke():Flow<PagingData<Movie>> {
        return repository.getPopularMovies(
            PagingConfig(pageSize = 20, initialLoadSize = 20)
        )
    }
}