package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailsState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    id: Int?,
    uiState: MovieDetailsState,
    onAddFavorite: (movie: Movie) -> Unit,
    checkedFavorite: (checkedFavorite: MovieDetailEvent.CheckFavorite) -> Unit,
    getMovieDetail: (MovieDetailEvent.GetMovieDetail) -> Unit
) {
    val pagingMoviesSimilar = uiState.result.collectAsLazyPagingItems()

    LaunchedEffect(true) {
        id?.let {
            getMovieDetail(MovieDetailEvent.GetMovieDetail(it))
            checkedFavorite(MovieDetailEvent.CheckFavorite(it))
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.detail_movie), color = white)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = black)
            )
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