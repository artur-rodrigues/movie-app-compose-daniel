package br.com.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingSource
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails

interface MovieDetailRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMovieSimilar(movieId: Int): PagingSource<Int, Movie>
}