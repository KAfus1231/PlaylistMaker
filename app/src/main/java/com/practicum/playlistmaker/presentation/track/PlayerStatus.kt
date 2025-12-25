package com.practicum.playlistmaker.presentation.track

data class PlayerStatus(
    val isPlaying: Boolean = false,
    val progress: Float = 0f // 0f .. 1f
) {
    companion object {
        val Initial = PlayerStatus(isPlaying = false, progress = 0f)
    }
}