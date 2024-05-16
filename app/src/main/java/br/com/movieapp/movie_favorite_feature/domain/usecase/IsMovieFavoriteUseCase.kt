package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import javax.inject.Inject

class IsMovieFavoriteUseCase @Inject constructor(
    private val repository: MovieFavoriteRepository
) : FavoriteUseCase<Int, Boolean>() {

    override suspend fun execute(param: Int): Boolean {
        return repository.isFavorite(param)
    }
}