package com.practicum.playlistmaker.domain.impl

import com.practicum.playlistmaker.domain.api.TrackSearchInteractor
import com.practicum.playlistmaker.domain.api.TracksRepository
import com.practicum.playlistmaker.domain.models.TrackModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackSearchInteractorImpl(
    private val repository: TracksRepository
) : TrackSearchInteractor {

    override fun searchTracks(expression: String) = repository.searchTracks(expression)

    override fun loadTrackData(trackId: String, onComplete: (TrackModel) -> Unit) {
        val tracks = repository.searchTracks("")
        val found = tracks.firstOrNull { it.trackName == trackId || it.trackName.contains(trackId) }
        val trackModel = found?.let {
            TrackModel(
                id = trackId,
                name = it.trackName,
                author = it.artistName,
                pictureUrl = ""
            )
        } ?: TrackModel(id = trackId, name = "Track $trackId", author = "Unknown", pictureUrl = "")
        onComplete(trackModel)
    }
}
