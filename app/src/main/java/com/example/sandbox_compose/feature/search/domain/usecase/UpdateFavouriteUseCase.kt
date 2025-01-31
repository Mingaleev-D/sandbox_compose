package com.example.sandbox_compose.feature.search.domain.usecase

import com.example.sandbox_compose.feature.search.domain.model.SearchArticle
import com.example.sandbox_compose.feature.search.domain.repository.SearchRepository

class UpdateFavouriteUseCase(
       private val repository: SearchRepository,
) {
    suspend operator fun invoke(article: SearchArticle) {
        repository.updateFavouriteArticle(article)
    }
}
