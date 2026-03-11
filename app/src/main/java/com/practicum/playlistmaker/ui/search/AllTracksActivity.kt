package com.practicum.playlistmaker.ui.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.practicum.playlistmaker.presentation.search.SearchViewModel

class AllTracksActivity : ComponentActivity() {

    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModel.getViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AllTracksScreen(
                    modifier = Modifier.padding(innerPadding),
                    viewModel = viewModel
                )
            }
        }
    }
}
