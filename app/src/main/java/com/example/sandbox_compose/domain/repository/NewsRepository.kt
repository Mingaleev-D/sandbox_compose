package com.example.sandbox_compose.domain.repository

import androidx.paging.PagingData
import com.example.sandbox_compose.data.model.RemoteEverythingNews
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources:List<String>): Flow<PagingData<RemoteEverythingNews.RemoteArticle>>
}
