package com.example.sandbox_compose.feature.search.domain.usecase

import androidx.paging.PagingData
import com.example.sandbox_compose.feature.search.domain.model.SearchArticle
import com.example.sandbox_compose.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class FetchSearchArticleUseCase(
       private val repository: SearchRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<SearchArticle>> =
           repository.fetchSearchArticles(query)
}
