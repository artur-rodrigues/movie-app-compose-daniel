package br.com.movieapp.movie_detail_feature.data.mapper

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.util.toBackdropUrl

fun MovieDetails.toMovie(): Movie {
    return Movie(id, title, voteAverage, backdropPath.toBackdropUrl())
}