package com.example.sandbox_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sandbox_compose.data.model.RemoteEverythingNews
import com.example.sandbox_compose.data.paging.NewsPagingSource
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
       private val apiService: ApiService
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<RemoteEverythingNews.RemoteArticle>> {
        return Pager(
               config = PagingConfig(pageSize = 10),
               pagingSourceFactory = {
                   NewsPagingSource(apiService = apiService, sources = sources.joinToString(separator = ","))
               }
        ).flow
    }
}
