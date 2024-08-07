package com.sivag.showtime.data.repository

import androidx.paging.PagingData
import com.sivag.network.client.ApiOperation
import com.sivag.showtime.data.model.remote.RemoteMovie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovies(): Flow<PagingData<RemoteMovie.Result>>
}