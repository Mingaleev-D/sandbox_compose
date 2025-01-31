package com.example.sandbox_compose.feature.search.domain.repository

import androidx.paging.PagingData
import com.example.sandbox_compose.feature.search.domain.model.SearchArticle
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun fetchSearchArticles(
           query: String,
    ): Flow<PagingData<SearchArticle>>

    suspend fun updateFavouriteArticle(article: SearchArticle)
}
