package com.sivag.showtime.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sivag.showtime.data.model.domain.PopularMovie
import com.sivag.showtime.data.model.domain.toPopularMovie
import com.sivag.showtime.data.model.remote.RemoteMovie
import com.sivag.showtime.navigation.Route
import com.sivag.showtime.ui.components.CustomMediumTopAppBar
import com.sivag.showtime.ui.components.ExtendedFabButton
import com.sivag.showtime.ui.components.MovieHCard
import com.sivag.showtime.ui.components.MovieVCard
import com.sivag.showtime.ui.components.WelcomeHeader
import com.sivag.showtime.viewmodel.MainViewModel
import kotlinx.serialization.json.Json

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {

    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    val movies = viewModel.fetchPopularMovies().collectAsLazyPagingItems()
    val mListState = rememberLazyListState()

    Scaffold(
        topBar = {
            CustomMediumTopAppBar(navController = navController,
                showSearchBar = showSearchBar,
                searchText = searchText,
                onSearchTextChanged = { searchText = it },
                title = "Home",
                isBack = false
            )
        },
        floatingActionButton = {
            ExtendedFabButton(listState = mListState){
            showSearchBar = !showSearchBar
                if (!showSearchBar) searchText = ""
        } }
    ) { innerPadding ->

        Column {
            PopularItem(navController = navController,
                listState = mListState, innerPadding = innerPadding, movies = movies)
        }
    }
}

@Composable
fun PopularItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    listState: LazyListState,
    innerPadding: PaddingValues,
    movies: LazyPagingItems<RemoteMovie.Result>
) {
    LazyColumn(
        modifier = modifier
            .padding(innerPadding),
        state = listState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { WelcomeHeader(name = "User") }
        item {
            Title(title = "Popular Movies")
            PopularHSItem(
                navController = navController,
                movies = movies
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Title(title = "Search Movies")
        }
        items(
            count =  movies.itemCount
        ) {
            // Display each movie
            val movie: PopularMovie = movies[it]!!.toPopularMovie()

            MovieHCard(popularMovie = movie) {
                val jsonString = Json.encodeToString(
                    PopularMovie.serializer(),
                    it)
                navController.navigate(Route.DetailScreen(jsonString))
            }
        }

        // Handle loading state
        movies.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }
                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = movies.loadState.refresh as LoadState.Error
                    item {
                        Text(text = "Error: ${e.error.message}")
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = movies.loadState.append as LoadState.Error
                    item {
                        Text(text = "Error: ${e.error.message}")
                    }
                }
            }
        }
    }
}

/**
 * Display each movie in a Horizontally Scrollable Item
 * */

@Composable
fun PopularHSItem(navController: NavController,movies: LazyPagingItems<RemoteMovie.Result>) {
    LazyRow() {
        items(
            count =  movies.itemCount
        ) {
            val movie: PopularMovie = movies[it]!!.toPopularMovie()

            MovieVCard(popularMovie = movie) {
                val jsonString = Json.encodeToString(
                    PopularMovie.serializer(),
                    movie)
                navController.navigate(Route.DetailScreen(jsonString))
            }
        }
    }
}