package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.domain.api.TrackPlayer
import com.practicum.playlistmaker.data.player.SimpleTrackPlayer

class MyApplication : Application() {

    fun provideTracksInteractor() = Creator.provideTrackSearchInteractor()

    fun provideTrackPlayer(): TrackPlayer {
        return SimpleTrackPlayer()
    }
}

