package br.com.movieapp.movie_search_feature.presentation

sealed class MoviesSearchEvent {
    data class EnteredQuery(val value: String): MoviesSearchEvent()
}