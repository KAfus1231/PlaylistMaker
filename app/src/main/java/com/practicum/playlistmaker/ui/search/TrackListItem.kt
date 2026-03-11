package com.practicum.playlistmaker.ui.search

import com.practicum.playlistmaker.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlistmaker.domain.models.Track

@Composable
fun TrackListItem(track: Track) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_music),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 12.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = track.trackName,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "${track.artistName} · ${track.trackTime}",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}