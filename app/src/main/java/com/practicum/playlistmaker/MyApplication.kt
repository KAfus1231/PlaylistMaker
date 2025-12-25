package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.data.TracksRepositoryImpl
import com.practicum.playlistmaker.domain.api.TracksRepository

class MyApplication: Application() {

    fun provideTrackSearchInteractor() = Creator.provideTrackSearchInteractor()
}
