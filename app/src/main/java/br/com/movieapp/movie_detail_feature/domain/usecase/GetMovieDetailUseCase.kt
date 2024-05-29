package br.com.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository
) {

    suspend fun invoke(movieId: Int, pagingConfig: PagingConfig): ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>> {
        return withContext(Dispatchers.IO) {
            ResultData.Loading
            try {
                val pagingSource = repository.getMovieSimilar(movieId)
                val movieDetails = repository.getMovieDetails(movieId)
                val pager = Pager(
                    config = pagingConfig,
                    pagingSourceFactory = {
                        pagingSource
                    }
                ).flow

                ResultData.Success(
                    pager to movieDetails
                )
            } catch (e: Exception) {
                ResultData.Failure(e)
            }
        }
    }
}