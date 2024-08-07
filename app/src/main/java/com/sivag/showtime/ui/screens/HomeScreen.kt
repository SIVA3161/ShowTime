package com.sivag.showtime.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sivag.network.client.ApiOperation
import com.sivag.showtime.data.model.domain.PopularMovie
import com.sivag.showtime.data.model.domain.toPopularMovie
import com.sivag.showtime.navigation.Route
import com.sivag.showtime.ui.components.ProgressLoader
import com.sivag.showtime.viewmodel.MainViewModel
import kotlinx.serialization.json.Json

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {

    val movies by viewModel.movies.collectAsState()

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (movies) {
                is ApiOperation.Loading -> {
                    ProgressLoader(isLoading = true)
                }

                is ApiOperation.Success -> {
                    ProgressLoader(isLoading = false)

                    //TODO starter purpose. start working on actual requirement
                    val movieList = (movies as ApiOperation.Success).data
                    LazyColumn {
                        items(movieList.size) {
                            movieList.forEach {
                                Column(
                                    Modifier
                                        .height(20.dp)
                                        .clickable {
                                            val jsonString = Json.encodeToString(
                                                PopularMovie.serializer(),
                                                it.toPopularMovie()
                                            )
                                            navController.navigate(
                                                Route.DetailScreen(
                                                    jsonString
                                                )
                                            )
                                        },
                                ) {
                                    Text(text = it.title)
                                }
                            }
                        }
                    }

                }

                is ApiOperation.Failure -> {
                    ProgressLoader(isLoading = false)
                    Text(text = "Failed to fetch movies")
                }

                else -> {
                    Text(text = "No data")
                }
            }
        }
    }
}