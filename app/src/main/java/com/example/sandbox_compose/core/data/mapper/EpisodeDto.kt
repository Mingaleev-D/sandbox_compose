package com.example.sandbox_compose.core.data.mapper

data class EpisodeDto(
       val id: Int,
       val name: String,
       val seasonNumber: Int,
       val episodeNumber: Int,
       val airDate: String,
       val characterIdsInEpisode: List<Int>
)
