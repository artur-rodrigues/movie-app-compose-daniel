package br.com.movieapp.core.paging

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import br.com.movieapp.movie_popular_feature.data.mapper.toMovie

class MovieSimilarPagingSource(
    private val remoteDetailDataSource: MovieDetailRemoteDataSource,
    private val movieId: Int
) : CommonPagingSource<Movie>() {

    override suspend fun executeCall(pageNumber: Int): List<Movie> {
        val response = remoteDetailDataSource.getMoviesSimilar(movieId, pageNumber)
        return response.movieResults.toMovie()
    }
}