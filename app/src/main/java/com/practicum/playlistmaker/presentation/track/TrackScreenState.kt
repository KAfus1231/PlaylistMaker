package com.practicum.playlistmaker.presentation.track

import com.practicum.playlistmaker.domain.models.TrackModel

sealed class TrackScreenState {
    object Loading : TrackScreenState()
    data class Content(val trackModel: TrackModel) : TrackScreenState()
}
