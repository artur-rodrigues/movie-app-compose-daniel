package br.com.movieapp.core.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.movieapp.core.presentation.navigation.CustomBottomNavigationBar
import br.com.movieapp.core.presentation.navigation.DetailScreenNav
import br.com.movieapp.core.presentation.navigation.NavigationGraph
import br.com.movieapp.core.presentation.navigation.currentRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(
        content = {
            NavigationGraph(navController = navHostController, it)
        },
        bottomBar = {
            if(currentRoute(navHostController = navHostController) != DetailScreenNav.DetailScreen.route) {
                CustomBottomNavigationBar(navController = navHostController)
            }
        }
    )
}