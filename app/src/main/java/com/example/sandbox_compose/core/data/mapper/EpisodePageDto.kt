package com.example.sandbox_compose.core.data.mapper

data class EpisodePageDto(
       val info: Info,
       val episodes: List<EpisodeDto>
) {
    data class Info(
           val count: Int,
           val pages: Int,
           val next: String?,
           val prev: String?
    )
}
