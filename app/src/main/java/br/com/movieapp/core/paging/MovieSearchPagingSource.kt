package br.com.movieapp.core.paging

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_search_feature.data.mapper.toMovieSearch
import br.com.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
) : CommonPagingSource<Movie>() {

    override suspend fun executeCall(pageNumber: Int): List<Movie> {
        val response = remoteDataSource.getSearchMovies(pageNumber, query)
        return response.searchResults.toMovieSearch()
    }
}