package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.commom.MovieGrid

@Composable
fun MovieDetailSimilarMovies(
    pagingMovieSimilar: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier
) {
    MovieGrid(
        modifier = modifier,
        pagingMovie = pagingMovieSimilar,
        isLoading = remember { mutableStateOf(true) },
        onDetail = {}
    )
}