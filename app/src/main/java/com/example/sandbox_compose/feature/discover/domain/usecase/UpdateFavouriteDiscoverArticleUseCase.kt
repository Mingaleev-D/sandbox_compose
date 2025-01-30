package com.example.sandbox_compose.feature.discover.domain.usecase

import com.example.sandbox_compose.feature.discover.domain.repository.DiscoverRepository
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle

class UpdateFavouriteDiscoverArticleUseCase(
       private val repository: DiscoverRepository,
) {
    suspend operator fun invoke(article: NewsyArticle) {
        repository.updateFavouriteDiscoverCategory(article)
    }
}
