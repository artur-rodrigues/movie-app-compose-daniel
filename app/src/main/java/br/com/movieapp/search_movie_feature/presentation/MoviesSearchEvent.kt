package br.com.movieapp.search_movie_feature.presentation

sealed class MoviesSearchEvent {
    data class EnteredQuery(val value: String): MoviesSearchEvent()
}