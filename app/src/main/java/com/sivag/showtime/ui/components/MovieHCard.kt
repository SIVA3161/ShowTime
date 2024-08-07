package com.sivag.showtime.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sivag.showtime.BuildConfig
import com.sivag.showtime.R
import com.sivag.showtime.data.model.domain.PopularMovie

@Composable
fun MovieHCard(popularMovie: PopularMovie, onItemClicked: (popularMovie: PopularMovie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(popularMovie) }),
        colors = CardDefaults.cardColors(
            contentColor =  MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            AnimatedAsyncImage(modifier = Modifier
                .size(80.dp, 80.dp)
                .clip(RoundedCornerShape(16.dp)),
                imageUrl = "${BuildConfig.IMAGE_BASE_URL_MEDIUM}${popularMovie.posterPath}",
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = popularMovie.title,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = buildString {
                        append("viewers | ")
                        append(if (popularMovie.adult) "18+ only" else "all")
                    },
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelSmall
                )

                Row(verticalAlignment = Alignment.Bottom) {

                    val ratingIcon: Painter = painterResource(id = R.drawable.ic_half_star)

                    Icon(
                        painter = ratingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp, 16.dp),
                        tint = Color.Blue
                    )

                    Text(
                        text = popularMovie.voteAverage.toString(),
                        modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
