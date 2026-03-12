package com.practicum.playlistmaker.data

import com.practicum.playlistmaker.data.dto.TracksSearchRequest
import com.practicum.playlistmaker.data.dto.TracksSearchResponse
import com.practicum.playlistmaker.domain.api.TracksRepository
import com.practicum.playlistmaker.domain.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay

private val listTracks = listOf(
    Track("Владивосток 2000", "Мумий Троль", "2:38"),
    Track("Группа крови", "Кино", "4:43"),
    Track("Не смотри назад", "Ария", "5:12"),
    Track("Звезда по имени Солнце", "Кино", "3:45"),
    Track("Лондон", "Аквариум", "4:32"),
    Track("На заре", "Альянс", "3:50"),
    Track("Перемен", "Кино", "4:56"),
    Track("Розовый фламинго", "Сплин", "3:15"),
    Track("Танцевать", "Мельница", "3:42"),
    Track("Чёрный бумер", "Серега", "4:01"),
)

class TracksRepositoryImpl(private val networkClient: NetworkClient) : TracksRepository {

    // <- сделали suspend и выполнили сетевой вызов в IO-контексте
    override suspend fun searchTracks(expression: String): List<Track> {
        return try {
            val response = withContext(Dispatchers.IO) {
                networkClient.doRequest(TracksSearchRequest(expression))
            }

            // безопасно приводим тип и проверяем код ответа
            if (response is TracksSearchResponse && response.resultCode == 200) {
                response.results.map { dto ->
                    val seconds = dto.trackTimeMillis / 1000
                    val minutes = seconds / 60
                    // формат mm:ss (например 4:05)
                    val trackTime = "%d:%02d".format(minutes, seconds - minutes * 60)
                    Track(dto.trackName, dto.artistName, trackTime)
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            // логирование при необходимости
            // Timber.e(e) или Log.e("TracksRepo", e.toString())
            emptyList()
        }
    }

    override suspend fun getAllTracks(): List<Track> {
        delay(1000)
        return listTracks
    }
}