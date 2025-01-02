package com.example.sandbox_compose.core.data.remote.model

import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.mapper.CharacterGender
import com.example.sandbox_compose.core.data.mapper.CharacterStatus
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class RemoteCharacter(
       val created: String,
       val episode: List<String>,
       val gender: String,
       val id: Int,
       val image: String,
       val location: Location,
       val name: String,
       val origin: Origin,
       val species: String,
       val status: String,
       val type: String,
       val url: String
) {
    @kotlinx.serialization.Serializable
    data class Location(
           val name: String,
           val url: String
    )

    @Serializable
    data class Origin(
           val name: String,
           val url: String
    )
}

fun RemoteCharacter.toDomainCharacter(): CharacterDto {
    val characterGender = when (gender.lowercase()) {
        "female" -> CharacterGender.Female
        "male" -> CharacterGender.Male
        "genderless" -> CharacterGender.Genderless
        else -> CharacterGender.Unknown
    }
    val characterStatus = when (status.lowercase()) {
        "alive" -> CharacterStatus.Alive
        "dead" -> CharacterStatus.Dead
        else -> CharacterStatus.Unknown
    }
    return CharacterDto(
           created = created,
           episodeUrls = episode,
           gender = characterGender,
           id = id,
           imageUrl = image,
           location = CharacterDto.Location(name = location.name, url = location.url),
           name = name,
           origin = CharacterDto.Origin(name = origin.name, url = origin.url),
           species = species,
           status = characterStatus,
           type = type
    )
}
