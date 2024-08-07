package com.sivag.showtime.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sivag.showtime.BuildConfig
import com.sivag.showtime.data.model.domain.PopularMovie

@Composable
fun MovieVCard(
    modifier: Modifier = Modifier,
    popularMovie: PopularMovie,
    onClick : () -> Unit
) {
    Card(
        modifier = modifier
            .padding(6.dp)
            .width(130.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            AnimatedAsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .height(170.dp)
                .clip(RoundedCornerShape(16.dp)),
                imageUrl = "${BuildConfig.IMAGE_BASE_URL_MEDIUM}${popularMovie.posterPath}",
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))
            val lineHeight = MaterialTheme.typography.headlineSmall.fontSize * 4 / 3
            Text(
                text = popularMovie.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 10.dp),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                lineHeight = lineHeight
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = popularMovie.releaseDate,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

}