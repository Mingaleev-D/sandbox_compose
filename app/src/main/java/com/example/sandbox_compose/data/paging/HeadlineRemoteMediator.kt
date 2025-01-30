package com.example.sandbox_compose.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.sandbox_compose.data.local.HeadlineDto
import com.example.sandbox_compose.data.local.HeadlineRemoteKey
import com.example.sandbox_compose.data.local.NewsyArticleDatabase
import com.example.sandbox_compose.data.mapper.Mapper
import com.example.sandbox_compose.data.model.ArticleDTO
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class HeadlineRemoteMediator(
       private val api: ApiService,
       private val database: NewsyArticleDatabase,
       private val articleHeadlineDtoMapper: Mapper<ArticleDTO, HeadlineDto>,
       private val category: String = "",
       private val country: String = "",
       private val language: String = "",
) : RemoteMediator<Int, HeadlineDto>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(20, TimeUnit.MINUTES)
        return if (
               System.currentTimeMillis() -
               (database.headlineRemoteDao().getCreationTime() ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
           loadType: LoadType,
           state: PagingState<Int, HeadlineDto>,
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKey?.prevKey
                prevKey ?: return MediatorResult.Success(
                       endOfPaginationReached = remoteKey != null
                )
            }

            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                val nextKey = remoteKey?.nextKey
                nextKey ?: return MediatorResult.Success(
                       endOfPaginationReached = remoteKey != null
                )
            }
        }
        return try {
            val headlineApiResponse = api.getHeadlines(
                   pageSize = state.config.pageSize,
                   category = category,
                   page = page,
                   country = country,
                   language = language,
            )
            val headlineArticles = headlineApiResponse.articles
            val endOfPaginationReached = headlineArticles!!.isEmpty()
            database.apply {
                if (loadType == LoadType.REFRESH) {
                    database.apply {
                        headlineRemoteDao().clearRemoteKeys()
                        headlineDao().removeAllHeadlineArticles()
                    }
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = headlineArticles.map {
                    HeadlineRemoteKey(
                           articleId = it.url!!,
                           prevKey = prevKey,
                           nextKey = nextKey,
                           currentPage = page
                    )
                }
                database.apply {
                    headlineRemoteDao().insertAll(remoteKeys)
                    headlineDao().insertHeadlineArticle(
                           articles = headlineArticles.map {
                               articleHeadlineDtoMapper.toModel(
                                      it
                               )
                           }
                    )
                }

            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (error: IOException) {
            MediatorResult.Error(error)
        } catch (error: HttpException) {
            MediatorResult.Error(error)
        }

    }

    private suspend fun getRemoteKeyForFirstItem(
           state: PagingState<Int, HeadlineDto>,
    ): HeadlineRemoteKey? {
        return state.pages.firstOrNull() {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { article ->
            database.headlineRemoteDao().getRemoteKeyByArticleId(article.url)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
           state: PagingState<Int, HeadlineDto>,
    ): HeadlineRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { id ->
                database.headlineRemoteDao().getRemoteKeyByArticleId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(
           state: PagingState<Int, HeadlineDto>,
    ): HeadlineRemoteKey? {
        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { article ->
            database.headlineRemoteDao().getRemoteKeyByArticleId(article.url)
        }
    }


}
