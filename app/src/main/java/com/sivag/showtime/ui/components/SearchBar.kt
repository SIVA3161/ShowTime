package com.sivag.showtime.ui.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sivag.showtime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DockedSearchBar(state: Boolean, closeClicked: () -> Unit) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val searchHistory = remember { mutableStateListOf<String>() }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state) {
        Log.w("ShowTimeLog", "DockedSearchBar : before: state: $state ; active: $active")
        active = state
        Log.i("ShowTimeLog", "DockedSearchBar : after : state: $state ; active: $active")

        if (active) {
            focusRequester.requestFocus()
            keyboardController?.show()
        } else {
            keyboardController?.hide()
        }
    }

    DockedSearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { newQuery ->
            println("Performing search on query: $newQuery")
            searchHistory.add(newQuery)
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = {
            Text(text = "Search for Movies..", style = MaterialTheme.typography.labelLarge)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon = {
            Row {
                IconButton(onClick = { /* open mic dialog */ }) {
                    Icon(painter = painterResource(R.drawable.ic_mic), contentDescription = "Mic")
                }
                if (active) {
                    IconButton(
                        onClick = {
                            closeClicked()
                            if (query.isNotEmpty()) {
                                query = ""
                            } else {
                                active = false
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            }
        },
        modifier = Modifier.focusRequester(focusRequester)
    ) {
        searchHistory.takeLast(3).forEach { item ->
            ListItem(
                modifier = Modifier
                    .wrapContentHeight()
                    .clickable { query = item },
                headlineContent = { Text(text = item) },
                leadingContent = {
                    Icon(
                        painter = painterResource(R.drawable.ic_history),
                        contentDescription = null
                    )
                }
            )
        }
    }
    
}

@Preview
@Composable
private fun SearchBarPreview() {
    DockedSearchBar(true) { }
}