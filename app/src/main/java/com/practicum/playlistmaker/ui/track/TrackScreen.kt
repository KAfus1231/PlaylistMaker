package com.practicum.playlistmaker.ui.track

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practicum.playlistmaker.presentation.track.TrackScreenState
import com.practicum.playlistmaker.presentation.track.PlayerStatus
import com.practicum.playlistmaker.presentation.track.TrackViewModel

@Composable
fun TrackScreen(viewModel: TrackViewModel) {
    val screenState by viewModel.trackScreenState.collectAsState()
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        when (screenState) {
            is TrackScreenState.Content -> {
                TrackScreenContent(viewModel, screenState as TrackScreenState.Content)
            }
            is TrackScreenState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackScreenContent(viewModel: TrackViewModel, screenState: TrackScreenState.Content) {
    val playerStatus by viewModel.playerStatusState.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        AsyncImage(model = screenState.trackModel.pictureUrl, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(240.dp))

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = screenState.trackModel.author, style = MaterialTheme.typography.titleMedium)
        Text(text = screenState.trackModel.name, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // play/pause button
        val buttonText = if (playerStatus.isPlaying) "Pause" else "Play"
        Button(onClick = {
            if (playerStatus.isPlaying) viewModel.pause() else viewModel.play()
        }) {
            Text(buttonText)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Slider for progress (0f .. 1f)
        Slider(value = playerStatus.progress, onValueChange = {
            viewModel.seek(it)
        }, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun AsyncImage(model: String, contentDescription: Nothing?, modifier: Modifier) {
    TODO("Not yet implemented")
}

