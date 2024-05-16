package br.com.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMovieSimilar(movieId: Int, pageConfig: PagingConfig): Flow<PagingData<Movie>>
}