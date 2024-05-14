package br.com.movieapp.core.presentation.components.commom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_popular_feature.presentation.components.MovieItem

@Composable
fun MovieGrid(
    pagingMovie: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    isLoading: MutableState<Boolean>,
    onDetail: (movieId: Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = paddingValues,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center
    ) {
        val lazyItem: LazyGridScope.(@Composable () -> Unit) -> Unit = {
            item(span = {  GridItemSpan(maxLineSpan) }) {
                it()
            }
        }

        pagingMovie.run {
            items(itemCount) {index ->
                val movie = get(index)

                movie?.let {
                    MovieItem(
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        id = it.id
                    ) { id ->
                        onDetail(id)
                    }
                }

                isLoading.value = false
            }
        }

        pagingMovie.run {
            when {
                isLoading.value -> {
                    lazyItem {
                        LoadingView()
                    }
                }

                loadState.refresh is LoadState.Error ||
                        loadState.append is LoadState.Error -> {
                    isLoading.value = false
                    lazyItem {
                        ErrorScreen(message = "Verifique sua conexão com a internet") {

                        }
                    }
                }
            }
        }
    }
}