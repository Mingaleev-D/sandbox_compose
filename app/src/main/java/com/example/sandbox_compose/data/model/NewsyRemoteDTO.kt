package com.example.sandbox_compose.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsyRemoteDTO(
    @SerialName("articles")
    val articles: List<ArticleDTO>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null
)
