package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import javax.inject.Inject

class AddMovieFavoriteUseCase @Inject constructor(
    private val repository: MovieFavoriteRepository
) : FavoriteUseCase<Movie, Unit>() {

    override suspend fun execute(param: Movie) {
        repository.insert(param)
    }
}