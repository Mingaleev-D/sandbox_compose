package com.example.sandbox_compose.core.data.mapper

data class CharacterPageDto(
       val info: Info,
       val characters: List<CharacterDto>
) {
    data class Info(
           val count: Int,
           val pages: Int,
           val next: String?,
           val prev: String?
    )
}
