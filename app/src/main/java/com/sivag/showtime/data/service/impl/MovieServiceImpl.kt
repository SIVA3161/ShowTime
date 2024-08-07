package com.sivag.showtime.data.service.impl

import com.sivag.network.client.ApiOperation
import com.sivag.network.client.safeApiCall
import com.sivag.showtime.BuildConfig
import com.sivag.showtime.data.model.remote.RemoteMovie
import com.sivag.showtime.data.model.domain.toPopularMovieList
import com.sivag.showtime.data.service.IMovieService
import com.sivag.showtime.utils.AppConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieServiceImpl(private val httpClient: HttpClient) : IMovieService {
    override suspend fun getPopularMovies(page: Int): ApiOperation<List<RemoteMovie.Result>> {
        return safeApiCall {
            httpClient.get(BuildConfig.BASE_URL + AppConstants.Api.ENDPOINT_POPULAR) {
                parameter(AppConstants.Api.KEY_LANGUAGE, AppConstants.Api.VALUE_EN_US)
                parameter(AppConstants.Api.KEY_PAGE, page)
                parameter(AppConstants.Api.KEY_API, BuildConfig.SECURE_API_KEY)
            }.body<RemoteMovie>()
                .toPopularMovieList()
        }
    }
}