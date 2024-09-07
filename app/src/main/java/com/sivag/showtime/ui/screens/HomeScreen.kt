package com.sivag.showtime.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.sivag.showtime.ui.components.CustomTopAppBar
import com.sivag.showtime.ui.components.ExtendedFabButton
import com.sivag.showtime.ui.components.MovieHCard
import com.sivag.showtime.ui.components.MovieVCard
import com.sivag.showtime.ui.components.PullToRefreshLazyColumn
import com.sivag.showtime.ui.components.WelcomeHeader
import com.sivag.showtime.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {

    var showSearchBar by remember { mutableStateOf(false) }

    val movies = viewModel.fetchPopularMovies().collectAsLazyPagingItems()
    val mListState = rememberLazyListState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Home",
                isBack = false
            )
        },
        floatingActionButton = {
            ExtendedFabButton(listState = mListState) {
                showSearchBar = !showSearchBar
            }
        }
    ) { innerPadding ->

        PopularItem(
            navController = navController,
            viewModel = viewModel,
            listState = mListState,
            innerPadding = innerPadding,
            movies = movies,
            showSearchBar = showSearchBar
        ){
            showSearchBar = false
        }
    }
}

@Composable
fun PopularItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel,
    listState: LazyListState,
    innerPadding: PaddingValues,
    movies: LazyPagingItems<RemoteMovie.Result>,
    showSearchBar: Boolean,
    searchCloseClicked: () -> Unit
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PullToRefreshLazyColumn(
            items = movies.itemSnapshotList,
            content = {
                Column(
                    modifier = modifier
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WelcomeHeader(name = "User",  showSearchBar = showSearchBar) { searchCloseClicked() }
                    Title(title = "Popular Movies")
                    PopularHSItem(
                        navController = navController,
                        movies = movies
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Title(title = "Search Movies")
                    movies.itemSnapshotList.forEachIndexed { index, _ ->
                        val movie: PopularMovie = movies[index]!!.toPopularMovie()

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
                                CircularProgressIndicator()
                            }
                            loadState.append is LoadState.Loading -> {
                                CircularProgressIndicator()
                            }
                            loadState.refresh is LoadState.Error -> {
                                val e = movies.loadState.refresh as LoadState.Error
                                Text(text = "Error: ${e.error.message}")
                            }
                            loadState.append is LoadState.Error -> {
                                val e = movies.loadState.append as LoadState.Error
                                Text(text = "Error: ${e.error.message}")
                            }
                        }
                    }
                }
            },
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    isRefreshing = true
                    viewModel.fetchPopularMovies()
                    isRefreshing = false
                }
            },
            lazyListState = listState
        )
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