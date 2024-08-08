package com.sivag.showtime.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.sivag.showtime.BuildConfig

object NetworkUtils {

    fun getApiHeader(): Map<String, String> =
        mapOf(
            "Authorization" to "Bearer ${BuildConfig.ACCESS_TOKEN}",
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )

    fun hasInternetConnection(context: Context?): Boolean {
        try {
            if (context == null)
                return false
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } catch (e: Exception) {
            return false
        }
    }
}