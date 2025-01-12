package com.example.sandbox_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sandbox_compose.data.mapper.toDomainModel
import com.example.sandbox_compose.data.mapper.toDomainModelList
import com.example.sandbox_compose.data.paging.SearchPagingSource
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.model.UnsplashImage
import com.example.sandbox_compose.domain.repository.ImageRepository
import com.example.sandbox_compose.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class ImageRepositoryImpl(
       private val apiService: ApiService
) : ImageRepository {

    override suspend fun getEditorialFeedImages(): List<UnsplashImage> {
        return apiService.getEditorialListImages().toDomainModelList()
    }

    override suspend fun getImage(imageId: String): UnsplashImage {
        return apiService.getEditorialImagesId(imageId).toDomainModel()
    }

    override fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
               config = PagingConfig(pageSize = ITEMS_PER_PAGE),
               pagingSourceFactory = { SearchPagingSource(query, apiService) }
        ).flow
    }
}
