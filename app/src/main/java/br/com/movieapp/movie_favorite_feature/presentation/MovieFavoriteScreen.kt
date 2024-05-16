package br.com.movieapp.movie_favorite_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.R
import br.com.movieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import br.com.movieapp.movie_favorite_feature.presentation.state.MovieFavoriteState
import br.com.movieapp.ui.theme.black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    modifier: Modifier = Modifier,
    navigateToDetailMovie: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.favorite_movies))
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = black)
            )
        }
    ) {
        MovieFavoriteContent(paddingValues = it, movies = uiState.movies) { movieId ->
            navigateToDetailMovie(movieId)
        }
    }
}

@Preview
@Composable
private fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(uiState = MovieFavoriteState()) {}
}