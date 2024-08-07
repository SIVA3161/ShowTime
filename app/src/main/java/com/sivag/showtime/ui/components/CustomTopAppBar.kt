package com.sivag.showtime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    isBack: Boolean,
    navController: NavController
) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            if (isBack) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Icon")
                }
            } else {
                IconButton(onClick = { /* no operation */ }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu Icon")
                }
            }
        },
        modifier = Modifier
            .background(Color.White),
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMediumTopAppBar(
    title: String,
    showSearchBar: Boolean,
    onSearchTextChanged: (String) -> Unit,
    searchText: String,
    isBack: Boolean,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    MediumTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            if (showSearchBar) {
                Column {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    BasicTextField(
                        value = searchText,
                        onValueChange = onSearchTextChanged,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            if (searchText.isEmpty()) {
                                Text(
                                    text = "Search Your favourite Movie Here",
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            } else {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        },
        navigationIcon = {
            if (isBack) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Icon")
                }
            } else {
                IconButton(onClick = { /* no operation */ }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu Icon")
                }
            }
        },
        modifier = Modifier
            .background(Color.White)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        scrollBehavior = scrollBehavior
    )
}
