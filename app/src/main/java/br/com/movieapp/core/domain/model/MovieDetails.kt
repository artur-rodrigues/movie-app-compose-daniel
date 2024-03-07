package br.com.movieapp.core.domain.model

import br.com.movieapp.core.data.remote.model.Genre

data class MovieDetails(
    val id: Int,
    val title: String,
    val genres: List<Genre>,
    val overview: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Double = 0.0,
    val duration: Int = 0,
    val voteCount: Int = 0,
)