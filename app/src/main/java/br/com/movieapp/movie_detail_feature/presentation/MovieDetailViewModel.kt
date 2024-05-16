package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailUseCase
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: GetMovieDetailUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailsState())
        private set

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun event(event: MovieDetailEvent) {
        when(event) {
            is MovieDetailEvent.GetMovieDetail -> getDetails(event.movieId)
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