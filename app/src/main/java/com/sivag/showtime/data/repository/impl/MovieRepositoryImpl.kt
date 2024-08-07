package com.sivag.showtime.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sivag.showtime.data.model.remote.RemoteMovie
import com.sivag.showtime.data.paging.MoviesPagingSource
import com.sivag.showtime.data.repository.IMovieRepository
import com.sivag.showtime.data.service.IMovieService
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val apiService: IMovieService) : IMovieRepository {
    override fun getPopularMovies(): Flow<PagingData<RemoteMovie.Result>> {
        return Pager(PagingConfig(pageSize = 5)) {
            MoviesPagingSource(apiService)
        }.flow
    }
}