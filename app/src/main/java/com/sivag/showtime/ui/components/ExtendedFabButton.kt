package com.sivag.showtime.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sivag.showtime.utils.isScrollingUp

@Composable
fun ExtendedFabButton(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        text = { Text("Search") },
        onClick = { onClick() },
        expanded = listState.isScrollingUp(),
        icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon" ) },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier.padding(16.dp)
    )
}