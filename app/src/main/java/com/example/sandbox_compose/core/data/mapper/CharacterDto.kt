package com.example.sandbox_compose.core.data.mapper

import androidx.compose.ui.graphics.Color

data class CharacterDto(
       val created: String,
       val episodeIds: List<Int>,
       val gender: CharacterGender,
       val id: Int,
       val imageUrl: String,
       val location: Location,
       val name: String,
       val origin: Origin,
       val species: String,
       val status: CharacterStatus,
       val type: String
) {
    data class Location(
           val name: String,
           val url: String
    )

    data class Origin(
           val name: String,
           val url: String
    )
}

sealed class CharacterGender(val displayName: String) {
    object Male: CharacterGender("Male")
    object Female: CharacterGender("Female")
    object Genderless: CharacterGender("No gender")
    object Unknown: CharacterGender("Not specified")
}

sealed class CharacterStatus(val displayName: String, val color: Color) {
    object Alive: CharacterStatus("Alive", Color.Green)
    object Dead: CharacterStatus("Dead", Color.Red)
    object Unknown: CharacterStatus("Unknown", Color.Yellow)
}
