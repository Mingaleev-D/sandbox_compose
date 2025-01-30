package com.example.sandbox_compose.feature.headline.domain.repository

import androidx.paging.PagingData
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import kotlinx.coroutines.flow.Flow

interface HeadlineRepository {
    fun fetchHeadlineArticle(
           category: String,
           country: String,
           language: String,
    ): Flow<PagingData<NewsyArticle>>

    suspend fun updateFavouriteArticle(newsyArticle: NewsyArticle)
}
