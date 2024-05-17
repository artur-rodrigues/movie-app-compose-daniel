package br.com.movieapp.core.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.yellow

@Composable
fun CustomBottomNavigationBar(
    navController: NavController,
) {
    val items = listOf(
        BottomNavItem.MoviePopular,
        BottomNavItem.MovieSearch,
        BottomNavItem.MovieFavorite
    )

    NavigationBar(
        containerColor = black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { destination ->
            BottomNavigationBarItem(currentRoute, destination, navController)
        }
    }
}

@Composable
fun RowScope.BottomNavigationBarItem(currentRoute: String?, destination: BottomNavItem, navController: NavController) {
    NavigationBarItem(
        selected = currentRoute == destination.route,
        onClick = {
            navController.navigate(destination.route) {
                launchSingleTop = true
            }
        },
        icon = {
            Icon(imageVector = destination.icon, contentDescription = null)
        },
        label = {
            Text(text = destination.title)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = yellow,
            selectedTextColor = yellow,
            indicatorColor = Color.Transparent,
            unselectedIconColor = yellow.copy(alpha = 0.5f),
            unselectedTextColor = yellow.copy(alpha = 0.5f)
        )
    )
}

@Composable
fun currentRoute(navHostController: NavHostController): String? {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    CustomBottomNavigationBar(navController = rememberNavController())
}