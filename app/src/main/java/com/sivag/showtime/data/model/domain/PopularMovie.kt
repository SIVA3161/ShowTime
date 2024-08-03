package com.sivag.showtime.data.model.domain

import com.sivag.showtime.data.model.remote.RemoteMovie

data class PopularMovie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)


fun RemoteMovie.toPopularMovieList(): List<RemoteMovie.Result> {
    return results
}
