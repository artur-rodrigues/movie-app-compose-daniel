package br.com.movieapp.movie_search_feature.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetMovieSearchUseCase @Inject constructor(
    private val repository: MovieSearchRepository
) {

    operator fun invoke(query: String, pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return try {
            Pager(
                config =  pagingConfig,
                pagingSourceFactory = {
                    repository.getSearchMovies(query)
                }
            ).flow
        } catch (e: Exception) {
            emptyFlow()
        }
    }
}