package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class CommonPagingSource<MODEL : Any> : PagingSource<Int, MODEL>() {

    abstract suspend fun executeCall(pageNumber: Int): List<MODEL>

    override fun getRefreshKey(state: PagingState<Int, MODEL>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(
                LIMIT
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MODEL> {
        return try {
            val pageNumber = params.key ?: 1

            val models = executeCall(pageNumber)

            LoadResult.Page(
                data = models,
                prevKey = if(pageNumber == 1) null else pageNumber - 1,
                nextKey = if(models.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}