package br.com.movieapp.search_movie_feature.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.search_movie_feature.presentation.components.SearchContent
import br.com.movieapp.search_movie_feature.presentation.state.MovieSearchState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

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
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.search_movies), color = white)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = black)
            )
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