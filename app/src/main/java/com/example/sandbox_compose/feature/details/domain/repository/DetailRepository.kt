package com.example.sandbox_compose.feature.details.domain.repository

import com.example.sandbox_compose.feature.details.domain.model.DetailArticle
import com.example.sandbox_compose.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getHeadlineArticleById(id: Int): Flow<Resource<DetailArticle>>
    suspend fun getDiscoverArticleById(id: Int): Flow<Resource<DetailArticle>>
   // suspend fun getSearchArticleById(id: Int): Flow<Resource<DetailArticle>>
}
