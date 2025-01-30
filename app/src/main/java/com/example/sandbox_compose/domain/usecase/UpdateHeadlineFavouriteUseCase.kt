package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.model.NewsyArticle
import com.example.sandbox_compose.domain.repository.HeadlineRepository

class UpdateHeadlineFavouriteUseCase(
       private val repository: HeadlineRepository,
) {
    suspend operator fun invoke(article: NewsyArticle) {
        repository.updateFavouriteArticle(article)
    }
}
