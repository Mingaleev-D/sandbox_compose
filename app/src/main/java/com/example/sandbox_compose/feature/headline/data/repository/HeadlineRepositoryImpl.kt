package com.example.sandbox_compose.feature.headline.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.core.data.model.ArticleDTO
import com.example.sandbox_compose.core.data.remote.ApiService
import com.example.sandbox_compose.feature.headline.data.local.HeadlineDto
import com.example.sandbox_compose.core.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.feature.headline.data.paging.HeadlineRemoteMediator
import com.example.sandbox_compose.feature.headline.domain.model.NewsyArticle
import com.example.sandbox_compose.feature.headline.domain.repository.HeadlineRepository
import com.example.sandbox_compose.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HeadlineRepositoryImpl @Inject constructor(
       private val headlineApi: ApiService,
       private val database: NewsyArticleDatabase,
       private val mapper: Mappers<HeadlineDto, NewsyArticle>,
       private val articleHeadlineMapper: Mappers<ArticleDTO, HeadlineDto>,
) : HeadlineRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchHeadlineArticle(
           category: String,
           country: String,
           language: String,
    ): Flow<PagingData<NewsyArticle>> {
        return Pager(
               PagingConfig(
                      pageSize = Constants.PAGE_SIZE,
                      prefetchDistance = Constants.PAGE_SIZE - 1,
                      initialLoadSize = 10
               ),
               remoteMediator = HeadlineRemoteMediator(
                      api = headlineApi,
                      database = database,
                      category = category,
                      country = country,
                      language = language,
                      articleHeadlineDtoMapper = articleHeadlineMapper
               )
        ) {
            database.headlineDao().getAllHeadlineArticles()
        }.flow.map { dtoPager ->
            dtoPager.map { dto ->
                mapper.toModel(dto)
            }
        }
    }

    override suspend fun updateFavouriteArticle(newsyArticle: NewsyArticle) {
        database.headlineDao().updateFavouriteArticle(
               isFavourite = newsyArticle.favourite,
               id = newsyArticle.id
        )
    }
}
