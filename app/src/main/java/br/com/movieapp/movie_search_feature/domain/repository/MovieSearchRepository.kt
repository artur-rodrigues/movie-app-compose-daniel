package br.com.movieapp.movie_search_feature.domain.repository

import androidx.paging.PagingSource
import br.com.movieapp.core.domain.model.Movie

interface MovieSearchRepository {

    fun getSearchMovies(query: String): PagingSource<Int,  Movie>
}