package com.example.sandbox_compose.core.data.remote.model

import com.example.sandbox_compose.core.data.mapper.EpisodeDto
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEpisode(
       val id: Int,
       val name: String,
       val episode: String,
       val air_date: String,
       val characters: List<String>
)

fun RemoteEpisode.toDomainEpisode(): EpisodeDto {
    return EpisodeDto(
           id = id,
           name = name,
           seasonNumber = episode.filter { it.isDigit() }.take(2).toInt(),
           episodeNumber = episode.filter { it.isDigit() }.takeLast(2).toInt(),
           airDate = air_date,
           characterIdsInEpisode = characters.map {
               it.substring(startIndex = it.lastIndexOf("/") + 1).toInt()
           }
    )
}
