package com.example.sandbox_compose.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEverythingNews(
    @SerialName("articles")
    val articles: List<RemoteArticle?>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null
) {
    @Serializable
    data class RemoteArticle(
        @SerialName("author")
        val author: String? = null,
        @SerialName("content")
        val content: String? = null,
        @SerialName("description")
        val description: String? = null,
        @SerialName("publishedAt")
        val publishedAt: String? = null,
        @SerialName("source")
        val source: RemoteSource? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("url")
        val url: String? = null,
        @SerialName("urlToImage")
        val urlToImage: String? = null
    ) {
        @Serializable
        data class RemoteSource(
            @SerialName("id")
            val id: String? = null,
            @SerialName("name")
            val name: String? = null
        )
    }
}
