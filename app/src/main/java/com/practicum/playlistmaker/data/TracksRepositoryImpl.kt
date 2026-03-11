package com.practicum.playlistmaker.data

import com.practicum.playlistmaker.data.dto.TracksSearchRequest
import com.practicum.playlistmaker.data.dto.TracksSearchResponse
import com.practicum.playlistmaker.domain.api.TracksRepository
import com.practicum.playlistmaker.domain.models.Track
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

    override fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        if (response.resultCode == 200) { // успешный запрос
            return (response as TracksSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d".format(minutes) + ":" + "%02d".format(seconds - minutes * 60)
                Track(it.trackName, it.artistName, trackTime) }
        } else {
            return emptyList()
        }
    }

    override suspend fun getAllTracks(): List<Track> {
        delay(1000)
        return listTracks
    }

}