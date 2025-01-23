package com.example.sandbox_compose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGameListItem(
       @SerialName("developer")
       val developer: String? = null,
       @SerialName("freetogame_profile_url")
       val freetogameProfileUrl: String? = null,
       @SerialName("game_url")
       val gameUrl: String? = null,
       @SerialName("genre")
       val genre: String? = null,
       @SerialName("id")
       val id: Int? = null,
       @SerialName("platform")
       val platform: String? = null,
       @SerialName("publisher")
       val publisher: String? = null,
       @SerialName("release_date")
       val releaseDate: String? = null,
       @SerialName("short_description")
       val shortDescription: String? = null,
       @SerialName("thumbnail")
       val thumbnail: String? = null,
       @SerialName("title")
       val title: String? = null
)
