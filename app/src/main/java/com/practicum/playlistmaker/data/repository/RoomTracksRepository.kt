package com.practicum.playlistmaker.data.repository

import com.practicum.playlistmaker.data.db.dao.TracksDao
import com.practicum.playlistmaker.data.mapper.TrackMapper
import com.practicum.playlistmaker.domain.api.TracksRepository
import com.practicum.playlistmaker.domain.models.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomTracksRepository(
    private val tracksDao: TracksDao
) : TracksRepository {

    override suspend fun searchTracks(expression: String): List<Track> {

        val list = tracksDao.getAll()

        return list.filter {
            it.trackName.contains(expression, ignoreCase = true) ||
                    it.artistName.contains(expression, ignoreCase = true)
        }.map {
            TrackMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun getAllTracks(): List<Track> {
        return tracksDao.getAll().map {
            TrackMapper.mapEntityToDomain(it)
        }
    }

    fun getFavoritesFlow(): Flow<List<Track>> =
        tracksDao.getFavoritesFlow().map { list ->
            list.map { TrackMapper.mapEntityToDomain(it) }
        }

    suspend fun insertEntity(entity: com.practicum.playlistmaker.data.db.entities.TrackEntity) {
        tracksDao.insert(entity)
    }
}