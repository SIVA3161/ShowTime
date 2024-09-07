
package com.sivag.showtime.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sivag.showtime.R

@Composable
fun WelcomeHeader(name: String, showSearchBar: Boolean, searchCloseClicked: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hello $name,",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(id = R.color.blue)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Get the popcorn, it’s showTime!",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            DockedSearchBar(state = showSearchBar) {
                searchCloseClicked()
            }

            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}


@Preview
@Composable
private fun TopBarPreview() {
    Column {
        WelcomeHeader("Sita", true) {

        }
    }
}