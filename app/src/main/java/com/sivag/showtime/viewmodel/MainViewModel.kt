package com.sivag.showtime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sivag.showtime.data.model.remote.RemoteMovie
import com.sivag.showtime.data.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val repository: IMovieRepository) : ViewModel() {

    private val popularMoviesData : Flow<PagingData<RemoteMovie.Result>> = repository.getPopularMovies().cachedIn(viewModelScope)


    init {
        fetchPopularMovies()
    }

   fun fetchPopularMovies(): Flow<PagingData<RemoteMovie.Result>> {
        return popularMoviesData
    }
}