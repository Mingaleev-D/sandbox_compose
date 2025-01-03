package com.example.sandbox_compose.core.data.remote.model

import com.example.sandbox_compose.core.data.mapper.EpisodePageDto
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEpisodePage(
       val info: Info,
       val results: List<RemoteEpisode>
) {

    @Serializable
    data class Info(
           val count: Int,
           val pages: Int,
           val next: String?,
           val prev: String?
    )
}

fun RemoteEpisodePage.toDomainEpisodePage(): EpisodePageDto {
    return EpisodePageDto(
           info = EpisodePageDto.Info(
                  count = info.count,
                  pages = info.pages,
                  next = info.next,
                  prev = info.prev
           ),
           episodes = results.map { it.toDomainEpisode() }
    )
}
