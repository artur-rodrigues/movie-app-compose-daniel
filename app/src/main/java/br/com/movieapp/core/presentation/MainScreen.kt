package br.com.movieapp.core.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.movieapp.core.presentation.navigation.CustomBottomNavigationBar
import br.com.movieapp.core.presentation.navigation.NavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(
        content = {
            NavigationGraph(navController = navHostController)
        },
        bottomBar = {
            CustomBottomNavigationBar(navController = navHostController)
        }
    )
}