package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.RemoteUnsplashImagesItem
import com.example.sandbox_compose.data.model.RemoteUnsplashImagesSearchResult
import com.example.sandbox_compose.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers(
           "Authorization: Client-ID ${Constants.API_KEY}"
    )
    @GET("photos")
    suspend fun getEditorialListImages(): List<RemoteUnsplashImagesItem>

    @Headers(
           "Authorization: Client-ID ${Constants.API_KEY}"
    )
    @GET("photos/{id}")
    suspend fun getEditorialImagesId(
           @Path("id") id: String
    ): RemoteUnsplashImagesItem

    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
           @Query("query") query: String,
           @Query("page") page: Int,
           @Query("per_page") perPage: Int
    ): RemoteUnsplashImagesSearchResult
}
