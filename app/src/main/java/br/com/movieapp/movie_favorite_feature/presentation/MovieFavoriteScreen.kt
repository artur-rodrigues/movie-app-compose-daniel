package br.com.movieapp.movie_favorite_feature.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.commom.MovieAppBar
import br.com.movieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    navigateToDetailMovie: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MovieAppBar(stringId = R.string.favorite_movies)
        }
    ) {
        MovieFavoriteContent(paddingValues = it, movies = movies) { movieId ->
            navigateToDetailMovie(movieId)
        }
    }
}

@Preview
@Composable
private fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(movies = emptyList()) {}
}