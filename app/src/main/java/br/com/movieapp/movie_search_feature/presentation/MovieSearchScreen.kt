package br.com.movieapp.movie_search_feature.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.commom.MovieAppBar
import br.com.movieapp.movie_search_feature.presentation.components.SearchContent
import br.com.movieapp.movie_search_feature.presentation.state.MovieSearchState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MoviesSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {
    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieAppBar(stringId = R.string.search_movies)
        }
    ) {
        SearchContent(
            paddingValues = it,
            pagingMovie = pagingMovies,
            query = uiState.query,
            onSearch = onFetch,
            onEvent = onEvent
        ) { movieId ->
            navigateToDetailMovie(movieId)
        }
    }
}