package com.example.sandbox_compose.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.model.UnsplashImage
import com.example.sandbox_compose.data.mapper.toDomainModel
import com.example.sandbox_compose.data.mapper.toDomainModelList
import com.example.sandbox_compose.data.model.RemoteUnsplashImagesItem

class SearchPagingSource(
       private val query: String,
       private val apiService: ApiService
): PagingSource<Int, UnsplashImage>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        Log.d("SearchPagingSource", "getRefreshKey: ${state.anchorPosition}")
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX
        Log.d("SearchPagingSource", "currentPage: $currentPage")
        return try {
            val response = apiService.searchImages(
                   query = query,
                   page = currentPage,
                   perPage = params.loadSize
            )
            val endOfPaginationReached = response.results.isEmpty()
            Log.d("SearchPagingSource", "Load Result response: ${response.results}")
            Log.d("SearchPagingSource", "endOfPaginationReached: $endOfPaginationReached")

            val domainImages = response.results as List<RemoteUnsplashImagesItem>
            LoadResult.Page(
                   data = domainImages.toDomainModelList(),
                   prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                   nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.d("SearchPagingSource", "LoadResultError: ${e.message}")
            LoadResult.Error(e)
        }
    }
}
