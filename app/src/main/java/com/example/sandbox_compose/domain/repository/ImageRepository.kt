package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.domain.model.UnsplashImage

interface ImageRepository {

    suspend fun getEditorialFeedImages(): List<UnsplashImage>
}
