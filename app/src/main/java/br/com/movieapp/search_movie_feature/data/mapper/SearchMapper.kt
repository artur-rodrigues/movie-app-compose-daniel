package br.com.movieapp.search_movie_feature.data.mapper

import br.com.movieapp.core.data.remote.model.SearchResult
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.util.toPostUrl

fun List<SearchResult>.toMovieSearch() = map {
    MovieSearch(
        it.id,
        it.voteAverage,
        it.posterPath.toPostUrl()
    )
}