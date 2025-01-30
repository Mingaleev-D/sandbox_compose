package com.example.sandbox_compose.feature.discover.data.mapper

import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.feature.discover.data.local.DiscoverArticleDto
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle

class DiscoverMapper: Mappers<DiscoverArticleDto, NewsyArticle> {
    override fun toModel(value: DiscoverArticleDto): NewsyArticle {
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

    override fun fromModel(value: NewsyArticle): DiscoverArticleDto {
        return value.run {
            DiscoverArticleDto(
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
