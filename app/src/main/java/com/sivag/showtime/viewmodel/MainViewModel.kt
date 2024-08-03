package com.sivag.showtime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sivag.network.client.ApiOperation
import com.sivag.showtime.data.model.remote.RemoteMovie
import com.sivag.showtime.data.repository.IMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: IMovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<ApiOperation<List<RemoteMovie.Result>>>(ApiOperation.Loading())
    val movies: StateFlow<ApiOperation<List<RemoteMovie.Result>>> get() = _movies

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            val apiOpData = repository.getPopularMovies()
            _movies.value = apiOpData
        }
    }
}