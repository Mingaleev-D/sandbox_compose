package com.example.sandbox_compose.feature.discover.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.sandbox_compose.core.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.core.data.remote.ApiService
import com.example.sandbox_compose.feature.discover.data.local.DiscoverArticleDto
import com.example.sandbox_compose.feature.discover.data.paging.DiscoverMediator
import com.example.sandbox_compose.feature.discover.domain.repository.DiscoverRepository
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import com.example.sandbox_compose.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DiscoverRepoImpl @Inject constructor(
       private val discoverApi: ApiService,
       private val database: NewsyArticleDatabase,
       private val mapper: Mappers<DiscoverArticleDto, NewsyArticle>,
) : DiscoverRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchDiscoverArticles(
           category: String,
           country: String,
           language: String,
    ): Flow<PagingData<NewsyArticle>> {
        return Pager(
               PagingConfig(
                      pageSize = Constants.PAGE_SIZE,
                      initialLoadSize = 10,
                      prefetchDistance = Constants.PAGE_SIZE - 1
               ),
               pagingSourceFactory = {
                   val data = database.discoverArticleDao().getDiscoverArticleDataSource(category)
                   data
               },
               remoteMediator = DiscoverMediator(
                      api = discoverApi,
                      database = database,
                      category = category,
                      country = country,
                      language = language
               )
        ).flow.map { dtoPager ->
            dtoPager.map { dto ->
                mapper.toModel(dto)
            }
        }
    }

    override suspend fun updateCategory(category: String) {
        database.discoverRemoteKeyDao().updateCategory(category)
    }

    override suspend fun getDiscoverCurrentCategory(): String {
        return database.discoverRemoteKeyDao().getCurrentCategory()
    }

    override suspend fun updateFavouriteDiscoverCategory(article: NewsyArticle) {
        database.discoverArticleDao().updateFavouriteArticle(
               isFavourite = article.favourite,
               id = article.id
        )
    }

    override suspend fun getAllAvailableCategories(): List<String> {
        return database.discoverRemoteKeyDao().getAllAvailableCategories()
    }
}
