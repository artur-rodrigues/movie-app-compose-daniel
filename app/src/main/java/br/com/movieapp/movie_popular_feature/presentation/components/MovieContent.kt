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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.commom.ErrorScreen
import br.com.movieapp.core.presentation.components.commom.LoadingView

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    pagingMovie: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onClick: (movieId: Int) -> Unit
) {
    Box(modifier = modifier.background(color = Color.Black)) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center
        ) {
            pagingMovie.run {
                items(itemCount) {index ->
                    val movie = get(index)

                    movie?.let {
                        MovieItem(
                            voteAverage = it.voteAverage,
                            imageUrl = it.imageUrl,
                            id = it.id
                        ) { id ->
                            
                        }
                    }
                }
            }

            val lazyItem: LazyGridScope.(@Composable () -> Unit) -> Unit = {
                item(span = {  GridItemSpan(maxLineSpan) }) {
                    it()
                }
            }

            pagingMovie.apply {
                when {
                    loadState.refresh is LoadState.Loading ||
                            loadState.append is LoadState.Loading -> {
                        lazyItem {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error ||
                            loadState.append is LoadState.Loading-> {
                        lazyItem {
                            ErrorScreen(message = "Verifique sua conexão com a internet") {

                            }
                        }
                    }
                }
            }
        }
    }
}
