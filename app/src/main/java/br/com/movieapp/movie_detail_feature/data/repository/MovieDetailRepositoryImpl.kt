package br.com.movieapp.movie_detail_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val dataSource: MovieDetailRemoteDataSource
) : MovieDetailRepository {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return dataSource.getMovieDetails(movieId)
    }

    override suspend fun getMovieSimilar(movieId: Int, pageConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = {
                dataSource.getSimilarMoviesPagingSource(movieId)
            }
        ).flow
    }
}