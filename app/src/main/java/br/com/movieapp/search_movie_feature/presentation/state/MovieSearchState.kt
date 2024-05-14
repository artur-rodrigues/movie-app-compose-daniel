package br.com.movieapp.search_movie_feature.presentation.state

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchState(
    val query: String = "",
    val movies: Flow<PagingData<Movie>> = emptyFlow()
) 