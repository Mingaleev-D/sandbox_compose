package com.example.sandbox_compose.core.data.model


import com.example.sandbox_compose.feature.headline.data.local.HeadlineDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDTO(
       @SerialName("author")
    val author: String? = null,
       @SerialName("content")
    val content: String? = null,
       @SerialName("description")
    val description: String? = null,
       @SerialName("publishedAt")
    val publishedAt: String? = null,
       @SerialName("source")
    val source: SourceDTO? = null,
       @SerialName("title")
    val title: String? = null,
       @SerialName("url")
    val url: String? = null,
       @SerialName("urlToImage")
    val urlToImage: String? = null
)

//fun ArticleDTO.toDiscoverArticle(page: Int, category: String): DiscoverArticleDto {
//    return DiscoverArticleDto(
//           author = author,
//           content = content ?: "empty value",
//           description = description ?: " empty value",
//           publishedAt = publishedAt,
//           title = title,
//           source = source.name,
//           category = category,
//           url = url,
//           urlToImage = urlToImage,
//           page = page
//    )
//}
//
//fun ArticleDTO.toHeadlineArticle(page: Int,category: String): HeadlineDto {
//    return HeadlineDto(
//           author = formatEmptyValue(author, "author"),
//           content = formatEmptyValue(content, "content"),
//           description = formatEmptyValue(description, "description"),
//           publishedAt = publishedAt ?: "",
//           source = source?.name ?: "",
//           title = title ?: "",
//           url = url ?: "",
//           urlToImage = urlToImage,
//           page = page,
//           category = category
//    )
//}
//
//
//private fun formatEmptyValue(value: String?, default: String = ""): String {
//    return value ?: "Unknown $default"
//}
