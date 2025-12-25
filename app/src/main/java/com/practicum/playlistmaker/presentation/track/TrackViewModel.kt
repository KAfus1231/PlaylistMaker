package com.practicum.playlistmaker.presentation.track

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practicum.playlistmaker.MyApplication
import com.practicum.playlistmaker.domain.api.TrackPlayer
import com.practicum.playlistmaker.domain.api.TrackSearchInteractor
import com.practicum.playlistmaker.presentation.track.PlayerStatus
import com.practicum.playlistmaker.presentation.track.TrackScreenState
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrackViewModel(
    private val trackId: String,
    private val tracksInteractor: TrackSearchInteractor,
    private val trackPlayer: TrackPlayer
) : ViewModel() {

    private val _trackScreenState = MutableStateFlow<TrackScreenState>(TrackScreenState.Loading)
    val trackScreenState: StateFlow<TrackScreenState> = _trackScreenState.asStateFlow()

    private val _playerStatusState = MutableStateFlow(PlayerStatus.Initial)
    val playerStatusState: StateFlow<PlayerStatus> = _playerStatusState.asStateFlow()

    init {
        Log.d("TEST", "TrackViewModel init!: $trackId")
        // Загружаем данные
        viewModelScope.launch {
            tracksInteractor.loadTrackData(trackId) { trackModel ->
                _trackScreenState.value = TrackScreenState.Content(trackModel)
            }
        }
    }

    private fun getCurrentPlayStatus(): PlayerStatus = _playerStatusState.value

    fun play() {
        trackPlayer.play(trackId, object : TrackPlayer.StatusObserver {
            override fun onProgress(progress: Float) {
                _playerStatusState.value = getCurrentPlayStatus().copy(progress = progress)
            }

            override fun onStop() {
                _playerStatusState.value = getCurrentPlayStatus().copy(isPlaying = false)
            }

            override fun onPlay() {
                _playerStatusState.value = getCurrentPlayStatus().copy(isPlaying = true)
            }
        })
    }

    fun pause() {
        trackPlayer.pause(trackId)
        _playerStatusState.value = getCurrentPlayStatus().copy(isPlaying = false)
    }

    fun seek(position: Float) {
        trackPlayer.seek(trackId, position)
        _playerStatusState.value = getCurrentPlayStatus().copy(progress = position)
    }

    override fun onCleared() {
        trackPlayer.release(trackId)
        super.onCleared()
    }

    companion object {
        fun getViewModelFactory(trackId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApp = this[APPLICATION_KEY] as MyApplication
                val interactor = myApp.provideTracksInteractor()
                val trackPlayer = myApp.provideTrackPlayer()

                TrackViewModel(
                    trackId,
                    interactor,
                    trackPlayer,
                )
            }
        }
    }
}
