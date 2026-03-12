package com.practicum.playlistmaker.domain.impl

import com.practicum.playlistmaker.domain.api.TrackSearchInteractor
import com.practicum.playlistmaker.domain.api.TracksRepository
import com.practicum.playlistmaker.domain.models.Track
import com.practicum.playlistmaker.domain.models.TrackModel

class TrackSearchInteractorImpl(
    private val repository: TracksRepository
) : TrackSearchInteractor {

    override suspend fun searchTracks(expression: String): List<Track> {
        return repository.searchTracks(expression)
    }

    override suspend fun loadTrackData(trackId: String): TrackModel {
        val tracks = repository.searchTracks("")

        val found = tracks.firstOrNull {
            it.trackName == trackId || it.trackName.contains(trackId)
        }

        return found?.let {
            TrackModel(
                id = trackId,
                name = it.trackName,
                author = it.artistName,
                pictureUrl = ""
            )
        } ?: TrackModel(
            id = trackId,
            name = "Track $trackId",
            author = "Unknown",
            pictureUrl = ""
        )
    }

    override suspend fun getAllTracks(): List<Track> {
        return repository.getAllTracks()
    }
}