package br.com.movieapp.movie_popular_feature.data.mapper

import br.com.movieapp.core.data.remote.model.MovieResult
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map {
    it.run {
        Movie(
            id = id,
            title = title,
            voteAverage = voteAverage,
            imageUrl = posterPath.toPostUrl().toString()
        )
    }
}