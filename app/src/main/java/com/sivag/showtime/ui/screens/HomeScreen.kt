package com.sivag.showtime.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sivag.network.client.ApiOperation
import com.sivag.showtime.navigation.Route
import com.sivag.showtime.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel = getViewModel()) {

    val movies by viewModel.movies.collectAsState()

    when (movies) {
        is ApiOperation.Loading -> {
            CircularProgressIndicator()
        }
        is ApiOperation.Success -> {
            val movieList = (movies as ApiOperation.Success).data
            LazyColumn {
                items(movieList.size) {
                    movieList.forEach {
                        Column(
                            Modifier
                                .height(20.dp)
                                .clickable {
                                   navController.navigate(
                                       Route.DetailScreen(
                                       it.title
                                   )
                                   )
                                },
                            ) {
                            Text( text = it.title)
                        }
                    }
                }
            }

        }
        is ApiOperation.Failure -> {
            Text(text = "Failed to fetch movies")
        }
        else -> {
            Text(text = "No data")
        }
    }
}