package com.sivag.showtime.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sivag.showtime.R

@Composable
fun MovieInfoCard(name: String, contentLang: String, starRating: String) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (contentLang.equals("en", true)) "English" else "French", // dummy
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ChipComponent(
                        value = "New",
                        colorResource = colorResource(id = R.color.blue)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = name,
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {

                val ratingIcon: Painter = painterResource(id = R.drawable.ic_half_star)

                Icon(
                    painter = ratingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp, 16.dp),
                    tint = Color.Blue
                )

                Text(
                    text = starRating,
                    modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Preview
@Composable
private fun MovieInfoCardPreview() {
    MovieInfoCard(name = "Bahubali", contentLang = "en", starRating = "4")
}