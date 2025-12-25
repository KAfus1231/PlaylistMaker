package com.practicum.playlistmaker.ui.track

import com.practicum.playlistmaker.presentation.track.TrackViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class TrackActivity : ComponentActivity() {

    private val viewModel by viewModels<TrackViewModel> {
        TrackViewModel.getViewModelFactory("123")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackScreen(viewModel)
        }
    }

    private fun changeProgressBarVisibility(visible: Boolean) {
        Log.d("TEST", "changeProgressBarVisibility: visible-$visible")
    }
}


@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Пока пусто
    }
}