package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesFavoriteUseCase @Inject constructor(
    private val repository: MovieFavoriteRepository
) {

    fun invoke(): Flow<List<Movie>> {
        return repository.getMovies()
    }
}