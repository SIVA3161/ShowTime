package com.sivag.showtime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sivag.showtime.ui.screens.DetailScreen
import com.sivag.showtime.ui.screens.HomeScreen
import com.sivag.showtime.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SetupNavHost(
    navController: NavHostController,
    startDestination: Route = Route.HomeScreen
) {
    val mainViewModel : MainViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){

        composable<Route.HomeScreen> {
            HomeScreen(navController, mainViewModel)
        }

        composable<Route.DetailScreen>() {
            DetailScreen(navController, navBackStackEntry = it)
        }
    }
}