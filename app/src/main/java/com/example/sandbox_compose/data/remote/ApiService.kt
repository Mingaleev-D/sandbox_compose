package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.RemoteUnsplashImagesItem
import com.example.sandbox_compose.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
           "Authorization: Client-ID ${Constants.API_KEY}"
    )
    @GET("photos")
    suspend fun getEditorialListImages(): List<RemoteUnsplashImagesItem>
}
