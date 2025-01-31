package com.example.sandbox_compose.feature.search.data.mapper

import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.feature.search.data.local.SearchDto
import com.example.sandbox_compose.feature.search.domain.model.SearchArticle

class SearchMapper : Mappers<SearchDto, SearchArticle> {

    override fun toModel(value: SearchDto): SearchArticle {
        return value.run {
            SearchArticle(
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

    override fun fromModel(value: SearchArticle): SearchDto {
        return value.run {
            SearchDto(
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
