package com.practicum.playlistmaker.data.db.dao

import androidx.room.*
import com.practicum.playlistmaker.data.db.entities.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TracksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: TrackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tracks: List<TrackEntity>)

    @Query("SELECT * FROM tracks")
    fun getAllFlow(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM tracks")
    suspend fun getAll(): List<TrackEntity>

    @Query("SELECT * FROM tracks WHERE trackId = :id LIMIT 1")
    suspend fun getById(id: String): TrackEntity?

    @Query("SELECT * FROM tracks WHERE isFavorite = 1")
    fun getFavoritesFlow(): Flow<List<TrackEntity>>

    @Query("DELETE FROM tracks WHERE trackId = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM tracks WHERE playlistId = :playlistId")
    fun getByPlaylistFlow(playlistId: Long): Flow<List<TrackEntity>>

    @Update
    suspend fun update(track: TrackEntity)
}