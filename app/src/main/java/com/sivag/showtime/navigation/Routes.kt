package com.sivag.showtime.navigation

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
        var name: String
    ): Route()
}