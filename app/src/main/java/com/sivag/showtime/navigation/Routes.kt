package com.sivag.showtime.navigation

import com.sivag.showtime.data.model.domain.PopularMovie
import kotlinx.serialization.Serializable

/**
 *
 * */
@Serializable
sealed class Route {

    @Serializable
    data object HomeScreen: Route()

    @Serializable
    data class DetailScreen(
        var data: String
    ): Route()
}