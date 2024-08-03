package com.sivag.showtime.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.sivag.showtime.navigation.Route

@Composable
fun DetailScreen(navBackStackEntry: NavBackStackEntry) {
    val args = navBackStackEntry.toRoute<Route.DetailScreen>()
    Text(
        text = "Name: ${args.name}",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}
