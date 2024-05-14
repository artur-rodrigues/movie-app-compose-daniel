package br.com.movieapp.search_movie_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.commom.MovieGrid
import br.com.movieapp.search_movie_feature.presentation.MoviesSearchEvent
import br.com.movieapp.ui.theme.black

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovie: LazyPagingItems<Movie>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MoviesSearchEvent) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {
    val isLoading = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(color = black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading.value = true
                onSearch(it)
            },
            onQuerySearchEvent = onEvent,
            modifier = Modifier.padding(8.dp)
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        MovieGrid(
            pagingMovie = pagingMovie,
            isLoading = isLoading,
            onDetail = onDetail
        )
    }
}