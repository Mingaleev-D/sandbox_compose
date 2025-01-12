package com.example.sandbox_compose.domain.repository

import androidx.paging.PagingData
import com.example.sandbox_compose.domain.model.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getEditorialFeedImages(): List<UnsplashImage>
     suspend fun getImage(imageId: String): UnsplashImage
    fun searchImages(query: String): Flow<PagingData<UnsplashImage>>
}
