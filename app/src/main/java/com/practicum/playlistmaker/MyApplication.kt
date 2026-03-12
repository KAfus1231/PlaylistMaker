package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.data.db.AppDatabase
import com.practicum.playlistmaker.data.repository.RoomTracksRepository
import com.practicum.playlistmaker.data.player.SimpleTrackPlayer
import com.practicum.playlistmaker.domain.api.TrackPlayer
import com.practicum.playlistmaker.domain.impl.TrackSearchInteractorImpl

class MyApplication : Application() {

    // инициализируем БД один раз
    val database by lazy { AppDatabase.getInstance(this) }

    // репозиторий на основе Room (если есть)
    private val roomTracksRepository by lazy {
        RoomTracksRepository(database.tracksDao())
    }

    // Interactor, который будет использовать реальный репозиторий
    fun provideTracksInteractor() = TrackSearchInteractorImpl(roomTracksRepository)

    // Плеер (оставляем как есть)
    fun provideTrackPlayer(): TrackPlayer = SimpleTrackPlayer()
}