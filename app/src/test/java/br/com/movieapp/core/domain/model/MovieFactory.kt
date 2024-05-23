package br.com.movieapp.core.domain.model
sealed class Poster {
    object Avengers: Poster()
    object JohnWick: Poster()
}

fun Poster.toMovie() = createMovie(this)

fun createMovies() = listOf(
    Poster.Avengers.toMovie(),
    Poster.JohnWick.toMovie()
)

fun createMovie(poster: Poster) = when(poster) {
    Poster.Avengers -> Movie(
        id = 1,
        title = "Avengers",
        voteAverage = 7.1,
        imageUrl = "URL"
    )
    Poster.JohnWick -> Movie(
        id = 2,
        title = "JohnWick",
        voteAverage = 7.2,
        imageUrl = "URL"
    )
}