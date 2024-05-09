package br.com.movieapp.core.paging

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_popular_feature.data.mapper.toMovie
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource

class MoviePagingSource(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : CommonPagingSource<Movie>() {

    override suspend fun executeCall(pageNumber: Int): List<Movie> {
        val response = remoteDataSource.getPopularMovie(pageNumber)
        return response.movieResults.toMovie()
    }
}