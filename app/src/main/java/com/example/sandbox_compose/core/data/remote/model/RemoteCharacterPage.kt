package com.example.sandbox_compose.core.data.remote.model

import com.example.sandbox_compose.core.data.mapper.CharacterPageDto
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCharacterPage(
       val info: Info,
       val results: List<RemoteCharacter>
) {
    @Serializable
    data class Info(
           val count: Int,
           val pages: Int,
           val next: String?,
           val prev: String?
    )
}

fun RemoteCharacterPage.toDomainCharacterPage(): CharacterPageDto {
    return CharacterPageDto(
           info = CharacterPageDto.Info(
                  count = info.count,
                  pages = info.pages,
                  next = info.next,
                  prev = info.next
           ),
           characters = results.map { it.toDomainCharacter() }
    )
}
