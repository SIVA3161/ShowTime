package com.sivag.network.sample

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * This is a Test Composable Function
 * */
@Composable
fun Sample(message: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = message,
    )
}