package br.com.movieapp.core.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.movieapp.core.presentation.navigation.BottomNavigationBar
import br.com.movieapp.core.presentation.navigation.NavigationGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navHostController)
        },
        content = {
            NavigationGraph(navController = navHostController)
        }
    )
}