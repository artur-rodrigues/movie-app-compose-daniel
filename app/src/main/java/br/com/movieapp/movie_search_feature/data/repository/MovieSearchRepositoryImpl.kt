package br.com.movieapp.movie_search_feature.data.repository

import androidx.paging.PagingSource
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.paging.MovieSearchPagingSource
import br.com.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
) : MovieSearchRepository {

    override fun getSearchMovies(query: String): PagingSource<Int, Movie> {
        return MovieSearchPagingSource(query, remoteDataSource)
    }
}