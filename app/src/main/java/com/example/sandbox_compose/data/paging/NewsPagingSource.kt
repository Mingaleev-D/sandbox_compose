package com.example.sandbox_compose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sandbox_compose.data.model.RemoteEverythingNews
import com.example.sandbox_compose.data.remote.ApiService

class NewsPagingSource(
       private val apiService: ApiService,
       private val sources: String
) : PagingSource<Int, RemoteEverythingNews.RemoteArticle>() {

    override fun getRefreshKey(state: PagingState<Int, RemoteEverythingNews.RemoteArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteEverythingNews.RemoteArticle> {
        val page = params.key ?: 1
        return try {
            val newsResponse = apiService.getEverythingNews(sources = sources, page = page)
            totalNewsCount += newsResponse.articles?.size ?: 1
            // val articles = newsResponse.articles.distinctBy { it.title } //Remove duplicates
            // Фильтрация null элементов
            val articles = newsResponse.articles?.filterNotNull()

            LoadResult.Page(
                   data = articles!!,
                   nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                   prevKey = if (page == 1) null else page - 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                   throwable = e
            )
        }
    }
}
