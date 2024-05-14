package br.com.movieapp.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.search_movie_feature.presentation.MovieSearchScreen
import br.com.movieapp.search_movie_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route,
        modifier = Modifier.padding(paddingValues)
    ){
        composable(BottomNavItem.MoviePopular.route) {
            val viewModel = hiltViewModel<MoviePopularViewModel>()
            MoviePopularScreen(uiState = viewModel.uiState) {

            }
        }

        composable(BottomNavItem.MovieSearch.route) {
            hiltViewModel<MovieSearchViewModel>().run {
                MovieSearchScreen(uiState, ::event, ::fetch) {

                }
            }
        }

        composable(BottomNavItem.MovieFavorite.route) {

        }
    }
}