package com.sivag.showtime.data.repository.impl

import com.sivag.network.client.ApiOperation
import com.sivag.showtime.data.model.remote.RemoteMovie
import com.sivag.showtime.data.repository.IMovieRepository
import com.sivag.showtime.data.service.IMovieService

class MovieRepositoryImpl(private val apiService: IMovieService): IMovieRepository {
    override suspend fun getPopularMovies(): ApiOperation<List<RemoteMovie.Result>> {
        return apiService.getPopularMovies()
    }
}