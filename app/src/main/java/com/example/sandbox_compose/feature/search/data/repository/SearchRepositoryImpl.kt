package com.example.sandbox_compose.feature.search.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.sandbox_compose.core.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.core.data.mapper.Mappers
import com.example.sandbox_compose.core.data.remote.ApiService
import com.example.sandbox_compose.feature.search.data.local.SearchDto
import com.example.sandbox_compose.feature.search.data.paging.SearchRemoteMediator
import com.example.sandbox_compose.feature.search.domain.model.SearchArticle
import com.example.sandbox_compose.feature.search.domain.repository.SearchRepository
import com.example.sandbox_compose.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
       private val api: ApiService,
       private val database: NewsyArticleDatabase,
       private val mapper: Mappers<SearchDto, SearchArticle>,
) : SearchRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchSearchArticles(query: String): Flow<PagingData<SearchArticle>> {
        return Pager(
               PagingConfig(pageSize = Constants.PAGE_SIZE),
               pagingSourceFactory = {
                   database.searchArticleDao().getAllSearchArticle()
               },
               remoteMediator = SearchRemoteMediator(
                      api, database, query
               )
        ).flow.map { pagerDto ->
            pagerDto.map {
                mapper.toModel(it)
            }
        }
    }

    override suspend fun updateFavouriteArticle(article: SearchArticle) {
        database.searchArticleDao().updateFavouriteArticle(
               isFavourite = article.favourite,
               id = article.id
        )
    }
}
