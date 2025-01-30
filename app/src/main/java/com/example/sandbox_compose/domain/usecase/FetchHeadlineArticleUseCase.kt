package com.example.sandbox_compose.domain.usecase

import androidx.paging.PagingData
import com.example.sandbox_compose.domain.model.NewsyArticle
import com.example.sandbox_compose.domain.repository.HeadlineRepository
import kotlinx.coroutines.flow.Flow

class FetchHeadlineArticleUseCase(
       private val repository: HeadlineRepository,
) {
    operator fun invoke(
           category: String,
           countryCode: String,
           languageCode: String,
    ): Flow<PagingData<NewsyArticle>> = repository.fetchHeadlineArticle(
           category, countryCode, languageCode
    )
}
