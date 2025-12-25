package com.practicum.playlistmaker.ui.playlist

import TrackViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels

class TrackActivity : ComponentActivity() {

    // Пример: используем простую фабрику с тривиальным id "123"
    private val viewModelSimple by viewModels<TrackViewModel> {
        TrackViewModel.getViewModelFactory("123")
    }

    // Или, если вы реализовали MyApplication и хотите получать interactor из Application:
    // private val viewModelWithApp by viewModels<TrackViewModel> {
    //     TrackViewModel.getViewModelFactoryWithApp("123")
    // }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Тестируем: при запуске Activity в лог должен попасть init
        Log.d("TEST", "TrackActivity created")
        // setContent { ... } — если нужно отображать Compose UI, подключай здесь
    }
}