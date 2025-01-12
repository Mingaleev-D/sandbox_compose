package com.example.sandbox_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.sandbox_compose.data.local.ImageVistaDatabase
import com.example.sandbox_compose.data.mapper.toDomainModel
import com.example.sandbox_compose.data.mapper.toDomainModelList
import com.example.sandbox_compose.data.mapper.toFavoriteImageEntity
import com.example.sandbox_compose.data.paging.SearchPagingSource
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.model.UnsplashImage
import com.example.sandbox_compose.domain.repository.ImageRepository
import com.example.sandbox_compose.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImageRepositoryImpl(
       private val apiService: ApiService,
       private val database: ImageVistaDatabase
) : ImageRepository {

    private val favoriteImagesDao = database.favoriteImagesDao()

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

    override fun getAllFavoriteImages(): Flow<PagingData<UnsplashImage>> {
        return Pager(
               config = PagingConfig(pageSize = ITEMS_PER_PAGE),
               pagingSourceFactory = { favoriteImagesDao.getAllFavoriteImages() }
        )
            .flow
            .map { pagingData ->
                pagingData.map { it.toDomainModel() }
            }
    }

    override suspend fun toggleFavoriteStatus(image: UnsplashImage) {
        val isFavorite = favoriteImagesDao.isImageFavorite(image.id)
        val favoriteImage = image.toFavoriteImageEntity()
        if (isFavorite) {
            favoriteImagesDao.deleteFavoriteImage(favoriteImage)
        } else {
            favoriteImagesDao.insertFavoriteImage(favoriteImage)
        }
    }

    override fun getFavoriteImageIds(): Flow<List<String>> {
        return favoriteImagesDao.getFavoriteImageIds()
    }
}
