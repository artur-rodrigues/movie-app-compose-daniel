package br.com.movieapp.core.domain.model

import androidx.paging.PagingSource
import androidx.paging.PagingState

fun createMoviePagingSource(movies: List<Movie>) = object : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return LoadResult.Page(
            data = movies,
            prevKey = null,
            nextKey = null
        )
    }

}