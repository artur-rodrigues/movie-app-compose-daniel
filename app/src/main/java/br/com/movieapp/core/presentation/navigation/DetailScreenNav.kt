package br.com.movieapp.core.presentation.navigation

import br.com.movieapp.core.util.Constants

sealed class DetailScreenNav(val route: String) {
    object DetailScreen: DetailScreenNav(
        route = "movie_detail_screen?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}={${Constants.MOVIE_DETAIL_ARGUMENT_KEY}}"
    ) {
        fun passMovieId(movieId: Int): String {
            return "movie_detail_screen?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=$movieId"
        }
    }
}