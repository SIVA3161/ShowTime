package com.sivag.showtime.utils

import com.sivag.showtime.BuildConfig

object NetworkUtils {

    fun getApiHeader(): Map<String, String> =
        mapOf(
            "Authorization" to "Bearer ${BuildConfig.ACCESS_TOKEN}",
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
}