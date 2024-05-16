package br.com.movieapp.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.core.util.Constants
import br.com.movieapp.movie_detail_feature.presentation.MovieDetailScreen
import br.com.movieapp.movie_detail_feature.presentation.MovieDetailViewModel
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.movie_search_feature.presentation.MovieSearchScreen
import br.com.movieapp.movie_search_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route,
        modifier = Modifier.padding(paddingValues)
    ){
        composable(BottomNavItem.MoviePopular.route) {
            hiltViewModel<MoviePopularViewModel>().run {
                MoviePopularScreen(uiState) {
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(it))
                }
            }
        }

        composable(BottomNavItem.MovieSearch.route) {
            hiltViewModel<MovieSearchViewModel>().run {
                MovieSearchScreen(uiState, ::event, ::fetch) {
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(it))
                }
            }
        }

        composable(
            route = BottomNavItem.MovieDetail.route,
            arguments = listOf(
                navArgument(
                    name = Constants.MOVIE_DETAIL_ARGUMENT_KEY,
                    builder = {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            )
        ) {entry ->
            hiltViewModel<MovieDetailViewModel>().run {
                MovieDetailScreen(
                    entry.arguments?.getInt(Constants.MOVIE_DETAIL_ARGUMENT_KEY),
                    uiState
                ) {
                    getMovieDetail(it)
                }
            }
        }

        composable(BottomNavItem.MovieFavorite.route) {
            hiltViewModel<MovieFavoriteViewModel>().run {
                MovieFavoriteScreen(uiState) {
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(it))
                }
            }
        }
    }
}