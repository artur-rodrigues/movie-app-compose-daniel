package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.commom.MovieAppBar
import br.com.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    uiState: MovieDetailsState,
    onAddFavorite: (movie: Movie) -> Unit,
) {
    val pagingMoviesSimilar = uiState.result.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier,
        topBar = {
            MovieAppBar(stringId = R.string.detail_movie)
        }
    ) {
        MovieDetailContent(
            movieDetails = uiState.movieDetails,
            pagingMoviesSimilar = pagingMoviesSimilar,
            isLoading = uiState.isLoading,
            isError = uiState.error,
            iconColor = uiState.iconColor,
            modifier = Modifier.padding(it)
        ) { movie ->
            onAddFavorite(movie)
        }
    }
}