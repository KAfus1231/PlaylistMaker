package com.practicum.playlistmaker.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey
    val trackId: String,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Long = 0L,
    val artworkUrl: String? = null,
    val isFavorite: Boolean = false,
    val playlistId: Long? = null
)