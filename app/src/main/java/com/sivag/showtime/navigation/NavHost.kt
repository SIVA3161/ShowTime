package com.sivag.showtime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sivag.showtime.ui.screens.DetailScreen
import com.sivag.showtime.ui.screens.HomeScreen


@Composable
fun SetupNavHost(
    navController: NavHostController,
    startDestination: Route = Route.HomeScreen
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){

        composable<Route.HomeScreen> {
            HomeScreen(navController)
        }

        composable<Route.DetailScreen> {
            DetailScreen(it)
        }
    }
}