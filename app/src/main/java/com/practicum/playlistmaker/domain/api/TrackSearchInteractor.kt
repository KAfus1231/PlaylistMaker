package com.practicum.playlistmaker.domain.api

import com.practicum.playlistmaker.domain.models.Track
import com.practicum.playlistmaker.domain.models.TrackModel

interface TrackSearchInteractor {

    suspend fun searchTracks(expression: String): List<Track>

    suspend fun loadTrackData(trackId: String): TrackModel

    suspend fun getAllTracks(): List<Track>
}