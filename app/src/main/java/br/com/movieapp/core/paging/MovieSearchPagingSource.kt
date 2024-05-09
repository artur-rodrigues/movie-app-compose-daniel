package br.com.movieapp.core.paging

import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.search_movie_feature.data.mapper.toMovieSearch
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
) : CommonPagingSource<MovieSearch>() {

    override suspend fun executeCall(pageNumber: Int): List<MovieSearch> {
        val response = remoteDataSource.getSearchMovies(pageNumber, query)
        return response.searchResults.toMovieSearch()
    }
}