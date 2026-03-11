package com.practicum.playlistmaker.presentation.search

import com.practicum.playlistmaker.domain.models.Track

sealed class SearchState {
    object Initial : SearchState()       // экран только открыт
    object Loading : SearchState()       // идёт загрузка
    data class Success(
        val foundList: List<Track>
    ) : SearchState()                    // данные успешно получены
    data class Error(
        val error: String
    ) : SearchState()                    // ошибка
}
