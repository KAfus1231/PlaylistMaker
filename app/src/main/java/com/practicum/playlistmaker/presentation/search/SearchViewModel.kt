package com.practicum.playlistmaker.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practicum.playlistmaker.MyApplication
import com.practicum.playlistmaker.domain.api.TrackSearchInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

class SearchViewModel(
    private val interactor: TrackSearchInteractor
) : ViewModel() {

    private val _allTracksScreenState =
        MutableStateFlow<SearchState>(SearchState.Initial)

    val allTracksScreenState: StateFlow<SearchState> =
        _allTracksScreenState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _allTracksScreenState.update { SearchState.Loading }

                val list = interactor.getAllTracks()

                _allTracksScreenState.update {
                    SearchState.Success(foundList = list)
                }
            } catch (e: IOException) {
                _allTracksScreenState.update {
                    SearchState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val app = this[APPLICATION_KEY] as MyApplication
                    SearchViewModel(
                        interactor = app.provideTracksInteractor()
                    )
                }
            }
    }
}
