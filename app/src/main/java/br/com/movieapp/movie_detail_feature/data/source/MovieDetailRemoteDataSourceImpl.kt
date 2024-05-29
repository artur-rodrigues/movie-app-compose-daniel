package br.com.movieapp.movie_detail_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.paging.MovieSimilarPagingSource
import br.com.movieapp.core.util.toBackdropUrl
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import javax.inject.Inject

class MovieDetailRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailRemoteDataSource {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return service.getMovie(movieId).run {
            MovieDetails(
                id = id,
                title = title,
                overview = overview,
                genres = genres,
                releaseDate = releaseDate,
                backdropPath = backdropPath.toBackdropUrl(),
                duration = runtime,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }

    override suspend fun getMoviesSimilar(movieId: Int, page: Int): MovieResponse {
        return service.getMoviesSimilar(movieId, page)
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(movieId,this)
    }
}