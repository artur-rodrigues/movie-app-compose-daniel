package br.com.movieapp.core.domain.model
sealed class PosterDetail {
    object Avengers: PosterDetail()
    object JohnWick: PosterDetail()
}

fun PosterDetail.toMovie() = createMovie(this)

fun createMovie(poster: PosterDetail) = when(poster) {
    PosterDetail.Avengers -> MovieDetails(
        id = 1,
        title = "Avengers",
        voteAverage = 7.1,
        backdropPath = "URL",
        genres = emptyList(),
        overview = "",
        releaseDate = ""
    )
    PosterDetail.JohnWick -> MovieDetails(
        id = 2,
        title = "JohnWick",
        voteAverage = 7.2,
        backdropPath = "URL",
        genres = emptyList(),
        overview = "",
        releaseDate = ""
    )
}