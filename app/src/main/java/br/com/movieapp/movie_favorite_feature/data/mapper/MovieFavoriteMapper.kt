package br.com.movieapp.movie_favorite_feature.data.mapper

import br.com.movieapp.core.data.local.entity.MovieEntity
import br.com.movieapp.core.domain.model.Movie

fun List<MovieEntity>.toMovies(): List<Movie> {
    return this.map {
        Movie(
            id = it.movieId,
            title = it.title,
            imageUrl = it.imgUrl
        )
    }
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(id, title ?: "N/A", imageUrl)
}