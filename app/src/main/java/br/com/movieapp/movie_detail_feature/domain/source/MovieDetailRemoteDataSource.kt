package br.com.movieapp.movie_detail_feature.domain.source

import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.paging.MovieSimilarPagingSource

interface MovieDetailRemoteDataSource {

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMoviesSimilar(movieId: Int, page: Int): MovieResponse

    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}