package br.com.movieapp.core.domain.model

import br.com.movieapp.core.data.remote.model.SearchResult
import br.com.movieapp.core.data.remote.response.SearchResponse

object MovieSearchPagingFactory {

    fun create() = SearchResponse(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        searchResults = listOf(
            SearchResult(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                backdropPath = "Url",
                adult = false,
                genreIds = emptyList(),
                originalTitle = "Avengers",
                originalLanguage = "",
                overview = "Overview",
                popularity = 2.2,
                posterPath = "poster",
                releaseDate = "",
                video = true,
                voteCount = 50,
                firstAirDate = "",
                mediaType = "",
                name = "",
                originalName = "",
                originCountry = emptyList()
            ),
            SearchResult(
                id = 2,
                title = "John Wick",
                voteAverage = 7.2,
                backdropPath = "Url",
                adult = false,
                genreIds = emptyList(),
                originalTitle = "John Wick",
                originalLanguage = "",
                overview = "Overview",
                popularity = 2.2,
                posterPath = "poster",
                releaseDate = "",
                video = true,
                voteCount = 50,
                firstAirDate = "",
                mediaType = "",
                name = "",
                originalName = "",
                originCountry = emptyList()
            )
        )
    )
}