package com.sivag.showtime.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toCapitalize(): String {
    return this.split(" ").joinToString(" ") { word ->
        word.lowercase().replaceFirstChar { it.uppercase() }
    }
}

fun String.extractYear(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(this, formatter)
    return date.year.toString()
}