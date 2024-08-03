package com.sivag.showtime.data.repository

import com.sivag.network.client.ApiOperation
import com.sivag.showtime.data.model.remote.RemoteMovie


interface IMovieRepository {
    suspend fun getPopularMovies(): ApiOperation<List<RemoteMovie.Result>>
}