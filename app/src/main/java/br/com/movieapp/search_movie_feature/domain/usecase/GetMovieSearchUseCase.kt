package br.com.movieapp.search_movie_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieSearchUseCase @Inject constructor(
    private val repository: MovieSearchRepository
) {

    operator fun invoke(query: String): Flow<PagingData<MovieSearch>> {
        return repository.getSearchMovies(
            query,
            PagingConfig(
                initialLoadSize = 20,
                pageSize = 20
            )
        )
    }
}