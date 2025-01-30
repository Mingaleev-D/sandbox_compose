package com.example.sandbox_compose.feature.headline.domain.usecase

import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import com.example.sandbox_compose.feature.headline.domain.repository.HeadlineRepository

class UpdateHeadlineFavouriteUseCase(
       private val repository: HeadlineRepository,
) {
    suspend operator fun invoke(article: NewsyArticle) {
        repository.updateFavouriteArticle(article)
    }
}
