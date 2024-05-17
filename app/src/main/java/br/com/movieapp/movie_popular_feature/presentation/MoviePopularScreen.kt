package br.com.movieapp.movie_popular_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.commom.MovieAppBar
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_popular_feature.presentation.components.MovieContent
import br.com.movieapp.movie_popular_feature.presentation.state.MoviePopularState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier,
        topBar = {
            MovieAppBar(stringId = R.string.popular_movies)
        }
    ) { paddingValues ->
        MovieContent(pagingMovie = movies, paddingValues = paddingValues) {
            UtilFunctions.logInfo("MOVIE_ID", it.toString())
            navigateToDetailMovie(it)
        }
    }
}