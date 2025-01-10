package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.mapper.toDomainModel
import com.example.sandbox_compose.data.mapper.toDomainModelList
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.model.UnsplashImage
import com.example.sandbox_compose.domain.repository.ImageRepository

class ImageRepositoryImpl(
       private val apiService: ApiService
) : ImageRepository {

    override suspend fun getEditorialFeedImages(): List<UnsplashImage> {
        return apiService.getEditorialListImages().toDomainModelList()
    }

    override suspend fun getImage(imageId: String): UnsplashImage {
        return apiService.getEditorialImagesId(imageId).toDomainModel()
    }
}
