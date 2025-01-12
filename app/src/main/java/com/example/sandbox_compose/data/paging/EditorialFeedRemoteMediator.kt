package com.example.sandbox_compose.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.APPEND
import androidx.paging.LoadType.PREPEND
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.sandbox_compose.data.local.ImageVistaDatabase
import com.example.sandbox_compose.data.local.UnsplashImageEntity
import com.example.sandbox_compose.data.local.UnsplashRemoteKeys
import com.example.sandbox_compose.data.mapper.toEntityList
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.utils.Constants.ITEMS_PER_PAGE

@OptIn(ExperimentalPagingApi::class)
class EditorialFeedRemoteMediator(
       private val apiService: ApiService,
       private val database: ImageVistaDatabase
) : RemoteMediator<Int, UnsplashImageEntity>() {

    companion object {

        private const val STARTING_PAGE_INDEX = 1
    }

    private val editorialFeedDao = database.editorialFeedDao()

    override suspend fun load(
           loadType: LoadType,
           state: PagingState<Int, UnsplashImageEntity>
    ): MediatorResult {
        try {
            val currentPage = when (loadType) {
                REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: STARTING_PAGE_INDEX
                }

                PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    Log.d("EditorialFeedRemoteMediator", "remoteKeysPrev: ${remoteKeys?.prevPage}")
                    val prevPage = remoteKeys?.prevPage
                                   ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }

                APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    Log.d("EditorialFeedRemoteMediator", "remoteKeysNext: ${remoteKeys?.nextPage}")
                    val nextPage = remoteKeys?.nextPage
                                   ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }
            val response =
                   apiService.getEditorialListImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()
            Log.d("EditorialFeedRemoteMediator", "endOfPaginationReached: $endOfPaginationReached")
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1
            Log.d("EditorialFeedRemoteMediator", "prevPage: $prevPage")
            Log.d("EditorialFeedRemoteMediator", "nextPage: $nextPage")

            database.withTransaction {
                if (loadType == REFRESH) {
                    editorialFeedDao.deleteAllEditorialFeedImages()
                    editorialFeedDao.deleteAllRemoteKeys()
                }
                val remoteKeys = response.map { unsplashImageDto ->
                    UnsplashRemoteKeys(
                           id = unsplashImageDto.id,
                           prevPage = prevPage,
                           nextPage = nextPage
                    )
                }
                editorialFeedDao.insertAllRemoteKeys(remoteKeys)
                editorialFeedDao.insertEditorialFeedImages(response.toEntityList())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.d("EditorialFeedRemoteMediator", "LoadResultError: ${e.message}")
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
           state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                editorialFeedDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
           state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                editorialFeedDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
           state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                editorialFeedDao.getRemoteKeys(id = unsplashImage.id)
            }
    }
}
