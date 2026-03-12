package com.practicum.playlistmaker.data.mapper

import com.practicum.playlistmaker.data.dto.TrackDto
import com.practicum.playlistmaker.data.db.entities.TrackEntity
import com.practicum.playlistmaker.domain.models.Track

object TrackMapper {

    fun mapDtoToEntity(dto: TrackDto): TrackEntity {
        return TrackEntity(
            trackId = dto.trackName + "_" + dto.artistName,
            trackName = dto.trackName,
            artistName = dto.artistName,
            trackTimeMillis = dto.trackTimeMillis.toLong()
        )
    }

    fun mapEntityToDomain(entity: TrackEntity): Track {
        return Track(
            trackName = entity.trackName,
            artistName = entity.artistName,
            trackTime = formatMillis(entity.trackTimeMillis)
        )
    }

    private fun formatMillis(ms: Long): String {
        val totalSec = (ms / 1000).toInt()
        val minutes = totalSec / 60
        val seconds = totalSec % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}