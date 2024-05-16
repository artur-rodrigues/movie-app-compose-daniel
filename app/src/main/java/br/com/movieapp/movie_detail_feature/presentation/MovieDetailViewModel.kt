package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailUseCase
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailsState
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.ui.theme.white
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: GetMovieDetailUseCase,
    private val isFavoriteUseCase: IsMovieFavoriteUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailsState())
        private set

    fun onAddFavorite(movie: Movie) {
        if (uiState.iconColor == white) {
            event(MovieDetailEvent.AddFavorite(movie))
        } else {
            event(MovieDetailEvent.RemoveFavorite(movie))
        }
    }

    fun checkedFavorite(checkedFavorite: MovieDetailEvent.CheckFavorite) {
        event(checkedFavorite)
    }

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun event(event: MovieDetailEvent) {
        when(event) {
            is MovieDetailEvent.GetMovieDetail -> getDetails(event.movieId)
            is MovieDetailEvent.AddFavorite -> addFavorite(event.movie)
            is MovieDetailEvent.RemoveFavorite -> removeFavorite(event.movie)
            is MovieDetailEvent.CheckFavorite -> isFavorite(event.movieId)
        }
    }

    private fun addFavorite(movie: Movie) {
        executeCall(
            errorMessage = "Erro ao cadastrar filme",
            exec = { addMovieFavoriteUseCase.invoke(movie) },
            update = { uiState = uiState.copy(iconColor = Color.Red) }
        )
    }

    private fun removeFavorite(movie: Movie) {
        executeCall(
            errorMessage = "Erro ao remover filme",
            exec = { deleteMovieFavoriteUseCase.invoke(movie) },
            update = { uiState = uiState.copy(iconColor = white) }
        )
    }

    private fun isFavorite(movieId: Int) {
        executeCall(
            errorMessage = "Erro ao remover filme",
            exec = { isFavoriteUseCase.invoke(movieId) },
            update = {
                uiState = if (it.data == true)  {
                    uiState.copy(iconColor = Color.Red)
                } else {
                    uiState.copy(iconColor = white)
                }
            }
        )
    }

    private fun <RETURN> executeCall(
        errorMessage: String,
        exec: () -> Flow<ResultData<RETURN>>,
        update: (ResultData.Success<RETURN>) -> Unit
    ) {
        viewModelScope.launch {
            exec().collectLatest {
                when(it) {
                    is ResultData.Success -> update(it)
                    is ResultData.Failure -> UtilFunctions.logError("DETAIL", errorMessage)
                    ResultData.Loading -> Unit
                }
            }
        }
    }

    private fun getDetails(movieId: Int) {
        viewModelScope.launch {
            useCase.invoke(movieId).collect {
                when(it) {
                    is ResultData.Failure -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = it.e?.message.toString()
                        )
                    }

                    ResultData.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is ResultData.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            movieDetails = it.data?.second,
                            result = it.data?.first ?: emptyFlow()
                        )
                    }
                }
            }
        }
    }
}