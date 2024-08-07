package com.sivag.showtime.data.service

import com.sivag.network.client.ApiOperation
import com.sivag.showtime.data.model.remote.RemoteMovie

interface IMovieService {
    suspend fun getPopularMovies(page: Int): ApiOperation<List<RemoteMovie.Result>>
}