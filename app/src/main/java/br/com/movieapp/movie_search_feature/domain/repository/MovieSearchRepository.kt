package br.com.movieapp.movie_search_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {

    fun getSearchMovies(query: String, pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}