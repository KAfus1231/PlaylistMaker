package com.practicum.playlistmaker.domain.api

import com.practicum.playlistmaker.domain.models.Track
import com.practicum.playlistmaker.domain.models.TrackModel

interface TrackSearchInteractor {
    fun searchTracks(expression: String): List<com.practicum.playlistmaker.domain.models.Track>

    fun loadTrackData(trackId: String, onComplete: (TrackModel) -> Unit)

    suspend fun getAllTracks():List<Track>
}
