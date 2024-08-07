package com.sivag.showtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.sivag.showtime.navigation.Route
import com.sivag.showtime.navigation.SetupNavHost
import com.sivag.showtime.ui.theme.ShowTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowTimeTheme {
                val navController = rememberNavController()
                SetupNavHost(navController = navController, Route.HomeScreen)
            }
        }
    }
}