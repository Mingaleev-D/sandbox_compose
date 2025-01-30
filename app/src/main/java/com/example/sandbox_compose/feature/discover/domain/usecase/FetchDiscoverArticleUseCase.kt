package com.example.sandbox_compose.feature.discover.domain.usecase

import androidx.paging.PagingData
import com.example.sandbox_compose.feature.discover.domain.repository.DiscoverRepository
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import kotlinx.coroutines.flow.Flow

class FetchDiscoverArticleUseCase(
       private val repository: DiscoverRepository
) {
    operator fun invoke(
           category:String,
           language:String,
           country:String
    ):Flow<PagingData<NewsyArticle>> =
           repository.fetchDiscoverArticles(category, country, language)
}
