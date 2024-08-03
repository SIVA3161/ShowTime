package com.sivag.showtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sivag.showtime.navigation.Route
import com.sivag.showtime.navigation.SetupNavHost
import com.sivag.showtime.ui.components.TopBar
import com.sivag.showtime.ui.theme.ShowTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowTimeTheme {
                val navController = rememberNavController()


                Scaffold(
                    topBar = { TopBar(onToggle = {}) }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        SetupNavHost(navController = navController, Route.HomeScreen)
                    }
                }

            }
        }
    }
}