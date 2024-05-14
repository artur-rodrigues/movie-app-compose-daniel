package br.com.movieapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.commom.ErrorScreen
import br.com.movieapp.core.presentation.components.commom.LoadingView
import br.com.movieapp.core.presentation.components.commom.MovieGrid

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    pagingMovie: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onDetail: (movieId: Int) -> Unit
) {
    Box(modifier = modifier.background(color = Color.Black)) {
        MovieGrid(
            pagingMovie = pagingMovie,
            paddingValues = paddingValues,
            isLoading = remember { mutableStateOf(true) },
            onDetail = onDetail
        )
    }
}
