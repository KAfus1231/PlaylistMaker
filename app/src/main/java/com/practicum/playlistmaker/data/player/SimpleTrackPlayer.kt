package com.practicum.playlistmaker.data.player

import com.practicum.playlistmaker.domain.api.TrackPlayer
import kotlinx.coroutines.*
import kotlin.math.min

class SimpleTrackPlayer : TrackPlayer {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private var job: Job? = null
    private var currentObserver: TrackPlayer.StatusObserver? = null
    private var currentTrackId: String? = null
    private var currentProgress = 0f

    override fun play(trackId: String, statusObserver: TrackPlayer.StatusObserver) {
        // если уже играет другой трек - release/stop it
        if (currentTrackId != trackId) {
            releaseInternal()
            currentTrackId = trackId
            currentProgress = 0f
        }
        currentObserver = statusObserver
        currentObserver?.onPlay()

        job?.cancel()
        job = scope.launch {
            // Простая имитация: каждые 200ms увеличиваем progress на 0.01
            while (isActive) {
                delay(200)
                currentProgress = min(1.0f, currentProgress + 0.01f)
                withContext(Dispatchers.Main) {
                    currentObserver?.onProgress(currentProgress)
                }
                if (currentProgress >= 1.0f) {
                    withContext(Dispatchers.Main) {
                        currentObserver?.onStop()
                    }
                    cancel()
                }
            }
        }
    }

    override fun pause(trackId: String) {
        if (currentTrackId == trackId) {
            job?.cancel()
            currentObserver?.onStop()
        }
    }

    override fun seek(trackId: String, position: Float) {
        if (currentTrackId == trackId) {
            currentProgress = position.coerceIn(0f, 1f)
            currentObserver?.onProgress(currentProgress)
        }
    }

    override fun release(trackId: String) {
        if (currentTrackId == trackId) {
            releaseInternal()
        }
    }

    private fun releaseInternal() {
        job?.cancel()
        job = null
        currentObserver = null
        currentTrackId = null
        currentProgress = 0f
    }
}
