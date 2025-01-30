package com.example.sandbox_compose.data.mapper

import com.example.sandbox_compose.data.local.HeadlineDto
import com.example.sandbox_compose.data.model.ArticleDTO
import com.example.sandbox_compose.domain.model.NewsyArticle

interface Mapper<T : Any?, Model : Any> {

    fun toModel(value: T): Model
    fun fromModel(value: Model): T
}

class HeadlineMapper:Mapper<HeadlineDto, NewsyArticle> {
    override fun toModel(value: HeadlineDto): NewsyArticle {
        return value.run {
            NewsyArticle(
                   id = id,
                   author = author,
                   content = content,
                   description = description,
                   publishedAt = publishedAt,
                   source = source,
                   title = title,
                   url = url,
                   urlToImage = urlToImage,
                   favourite = favourite,
                   category = category,
                   page = page
            )
        }
    }

    override fun fromModel(value: NewsyArticle): HeadlineDto {
        return value.run {
            HeadlineDto(
                   id = id,
                   author = author,
                   content = content,
                   description = description,
                   publishedAt = publishedAt,
                   source = source,
                   title = title,
                   url = url,
                   urlToImage = urlToImage,
                   favourite = favourite,
                   category = category,
                   page = page
            )
        }
    }
}

class ArticleHeadlineDtoMapper(
       private val page: Int = 0,
       private val category: String = "",
) : Mapper<ArticleDTO, HeadlineDto> {
    override fun toModel(value: ArticleDTO): HeadlineDto {
        return value.run {
            HeadlineDto(
                   author = formatEmptyValue(author, "author"),
                   content = formatEmptyValue(content, "content"),
                   description = formatEmptyValue(description, "description"),
                   publishedAt = publishedAt ?: "",
                   source = source?.name ?: "",
                   title = title ?: "",
                   url = url ?: "",
                   urlToImage = urlToImage,
                   page = page,
                   category = category,
            )
        }
    }

    override fun fromModel(value: HeadlineDto): ArticleDTO {
        return value.run {
            ArticleDTO(
                   author, content, description, publishedAt
            )
        }
    }

    private fun formatEmptyValue(value: String?, default: String = ""): String {
        return value ?: "Unknown $default"
    }
}
