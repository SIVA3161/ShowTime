package com.sivag.showtime.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Column {


        TopAppBar(
            title = {
                BasicTextField(
                    value = searchText,
                    onValueChange = onSearchTextChanged,
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = onCloseClicked) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                }
            },
            modifier = Modifier.height(80.dp)
        )
    }
}