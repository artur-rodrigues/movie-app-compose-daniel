package br.com.movieapp.movie_detail_feature.data.repository

import androidx.paging.PagingSource
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.paging.MovieSimilarPagingSource
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val dataSource: MovieDetailRemoteDataSource
) : MovieDetailRepository {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return dataSource.getMovieDetails(movieId)
    }

    override suspend fun getMovieSimilar(movieId: Int): PagingSource<Int, Movie> {
        return MovieSimilarPagingSource(movieId, dataSource)
    }
}