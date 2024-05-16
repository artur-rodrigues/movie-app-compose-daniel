package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.util.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class FavoriteUseCase<PARAM, RETURN> {

    abstract suspend fun execute(param: PARAM): RETURN

    fun invoke(param: PARAM): Flow<ResultData<RETURN>> = flow {
        try {
            emit(ResultData.Loading)
            emit(ResultData.Success(execute(param)))
        } catch (e: Exception) {
            emit(ResultData.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}