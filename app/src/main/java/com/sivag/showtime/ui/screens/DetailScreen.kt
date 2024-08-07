package com.sivag.showtime.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.sivag.showtime.R
import com.sivag.showtime.data.model.domain.PopularMovie
import com.sivag.showtime.navigation.Route
import com.sivag.showtime.ui.components.AnimatedAsyncImage
import com.sivag.showtime.ui.components.CustomTopAppBar
import com.sivag.showtime.ui.components.InfoCard
import com.sivag.showtime.ui.components.MovieInfoCard
import com.sivag.showtime.utils.extractYear
import com.sivag.showtime.utils.toCapitalize
import kotlinx.serialization.json.Json
import java.util.Locale

@Composable
fun DetailScreen(navController: NavController, navBackStackEntry: NavBackStackEntry) {
    val args = navBackStackEntry.toRoute<Route.DetailScreen>()
    val movieJson = args.data
    val popularMovie = Json.decodeFromString(PopularMovie.serializer(), movieJson)
    LaunchedEffect(key1 = Unit) {
        Log.i("ShowTime", "popularMovie : $popularMovie")
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(navController = navController, title = "Movie Detail", isBack = true)
        },
        content = { innerPadding ->
            DetailsView(innerPadding, popularMovie)
        }
    )
}

@Composable
fun DetailsView(innerPadding : PaddingValues, popularMovie: PopularMovie) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = colorResource(id = R.color.background))
    ) {

        // Header Item
        item {
            popularMovie.apply {
                AnimatedAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    imageUrl = "https://image.tmdb.org/t/p/w500/${popularMovie.posterPath}",
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(16.dp))
                MovieInfoCard(
                    popularMovie.title.toCapitalize(),
                    contentLang = popularMovie.originalLanguage,
                    starRating = "${String.format(
                        locale = Locale.ENGLISH, 
                        format = "%.1f", popularMovie.voteAverage
                    )} (${popularMovie.voteCount})"
                )
            }
        }

        // Movie Description
        item {
            popularMovie.apply {
                Spacer(modifier = Modifier.height(16.dp))
                Title(title = "Overview")
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = this.overview,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    color = colorResource(id = R.color.text),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
            }
        }

        // Movie info
        item {
            popularMovie.apply {

                Spacer(modifier = Modifier.height(24.dp))
                Title(title = "Movie info")
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoCard(title = "Content", value = if(popularMovie.adult) "18+" else "All Ages")
                    InfoCard(title = "Vote", value = popularMovie.voteCount.toString())
                    InfoCard(title = "Released", value = popularMovie.releaseDate.extractYear())
                }
            }
        }

        // Booking button
        item {
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                onClick = { /* no operation */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text("Book Now")
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = colorResource(id = R.color.text),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}
